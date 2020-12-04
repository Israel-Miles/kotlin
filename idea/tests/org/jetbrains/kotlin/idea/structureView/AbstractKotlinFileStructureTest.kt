/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.structureView

import com.intellij.ide.util.FileStructurePopup
import com.intellij.openapi.util.io.FileUtil
import com.jetbrains.rd.util.concurrentMapOf
import org.jetbrains.kotlin.idea.completion.test.configureWithExtraFile
import org.jetbrains.kotlin.idea.test.KotlinWithJdkAndRuntimeLightProjectDescriptor
import org.jetbrains.kotlin.idea.test.PluginTestCaseBase
import org.jetbrains.kotlin.test.InTextDirectivesUtils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

abstract class AbstractKotlinFileStructureTest : KotlinFileStructureTestBase() {
    override fun getTestDataPath() = PluginTestCaseBase.getTestDataPathBase() + "/structureView/fileStructure"

    override fun getProjectDescriptor() = KotlinWithJdkAndRuntimeLightProjectDescriptor.INSTANCE

    override val fileExtension = "kt"
    override val treeFileName: String get() = getFileName("after")

    private val threadDumpLoggingThreadPool = Executors.newScheduledThreadPool(1)
    private val threadStates = mutableMapOf<Long, Map<Thread, Array<StackTraceElement>>>()

    override fun setUp() {
        super.setUp()
        val task = {
            threadStates[System.currentTimeMillis()] = Thread.getAllStackTraces()
        }
        threadDumpLoggingThreadPool.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS)
    }

    override fun tearDown() {
        threadDumpLoggingThreadPool.shutdown()
        val timeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val header = "\n\n######################################\n\n"
        val message = header + threadStates.entries.joinToString(separator = header) { (timeMillis, threadsState) ->
            val threads = threadsState.entries.joinToString("\n\n") { (thread, traceElements) ->
                """Thread: ${thread.name}
                    |${traceElements.joinToString("\n")}
                """.trimMargin()
            }
            """State at: ${timeFormat.format(Date(timeMillis))}
                |$threads
            """.trimMargin()
        }
        LOG.debug(message)
        super.tearDown()
    }

    fun doTest(path: String) {
        myFixture.configureWithExtraFile(path)

        popupFixture.popup.setup()

        checkTree()
    }

    protected fun FileStructurePopup.setup() {
        val fileText = FileUtil.loadFile(File(testDataPath, fileName()), true)

        val withInherited = InTextDirectivesUtils.isDirectiveDefined(fileText, "WITH_INHERITED")
        setTreeActionState(KotlinInheritedMembersNodeProvider::class.java, withInherited)
    }
}
