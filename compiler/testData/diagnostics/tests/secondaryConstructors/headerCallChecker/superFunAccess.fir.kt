// !DIAGNOSTICS: -UNUSED_PARAMETER
open class B(x: Int) {
    fun foo() = 1
}
class A : B {
    constructor(x: Int, y: Int = x <!AMBIGUITY!>+<!> <!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!> <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.<!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!> super.<!UNRESOLVED_REFERENCE!>foo<!>()) :
        super(x <!AMBIGUITY!>+<!> <!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!> <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.<!UNRESOLVED_REFERENCE!>foo<!>() <!UNRESOLVED_REFERENCE!>+<!> super.<!UNRESOLVED_REFERENCE!>foo<!>())
}
