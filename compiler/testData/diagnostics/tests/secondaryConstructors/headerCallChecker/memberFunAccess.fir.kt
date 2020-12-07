// !DIAGNOSTICS: -UNUSED_PARAMETER
class A {
    fun foo() = 1
    constructor(x: Int)
    constructor(x: Int, y: Int, z: Int = x <!AMBIGUITY!>+<!> <!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!> <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.<!UNRESOLVED_REFERENCE!>foo<!>()) :
        this(x <!AMBIGUITY!>+<!> <!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!> <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.<!UNRESOLVED_REFERENCE!>foo<!>())
}
