// !DIAGNOSTICS: -UNUSED_PARAMETER

fun A.foobar() = 1
val A.prop: Int get() = 2

class A {
    constructor(x: Int)
    constructor() : this(
            <!UNRESOLVED_REFERENCE!>foobar<!>() <!UNRESOLVED_REFERENCE!>+<!>
            <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.foobar() <!UNRESOLVED_REFERENCE!>+<!>
            <!UNRESOLVED_REFERENCE!>prop<!> <!UNRESOLVED_REFERENCE!>+<!>
            <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.prop <!UNRESOLVED_REFERENCE!>+<!>
            <!UNRESOLVED_LABEL!>this@A<!>.prop
    )
}
