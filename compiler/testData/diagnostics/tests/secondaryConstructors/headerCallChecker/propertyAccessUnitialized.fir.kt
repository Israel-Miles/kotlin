// !DIAGNOSTICS: -UNUSED_PARAMETER
open class B(x: Int)
class A : B {
    val prop = 1
    constructor(x: Int, y: Int = x <!AMBIGUITY!>+<!> <!UNRESOLVED_REFERENCE!>prop<!> <!UNRESOLVED_REFERENCE!>+<!> <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.<!UNRESOLVED_REFERENCE!>prop<!>) :
        super(x <!AMBIGUITY!>+<!> <!UNRESOLVED_REFERENCE!>prop<!> <!UNRESOLVED_REFERENCE!>+<!> <!INSTANCE_ACCESS_BEFORE_SUPER_CALL!>this<!>.<!UNRESOLVED_REFERENCE!>prop<!>)
}
