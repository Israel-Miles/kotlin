// !DIAGNOSTICS: -UNUSED_PARAMETER

fun A.foobar() = 3

class A {
    fun foo() = 1
    constructor(x: () -> Int)
    constructor() : this(
            {
                <!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!>
                <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.<!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!>
                <!UNRESOLVED_LABEL!>this@A<!>.<!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!>
                <!UNRESOLVED_REFERENCE!>foobar<!>()
            })
}
