/**
  * Version: Week 6: Lazy
  * Source: https://weblab.tudelft.nl/ti2606/2017-2018/note/167
  */
object Untyped {

  // abstract syntax
  sealed abstract class ExprExt
  case class TrueExt() extends ExprExt
  case class FalseExt() extends ExprExt
  case class NumExt(num: Int) extends ExprExt
  case class BinOpExt(s: String, l: ExprExt, r: ExprExt) extends ExprExt
  case class UnOpExt(s: String, e: ExprExt) extends ExprExt
  case class IfExt(c: ExprExt, t: ExprExt, e: ExprExt) extends ExprExt
  case class AppExt(f: ExprExt, a: List[ExprExt]) extends ExprExt
  case class IdExt(c: String) extends ExprExt
  case class FdExt(param: List[String], body: ExprExt) extends ExprExt
  case class LetExt(binds: List[LetBindExt], body: ExprExt) extends ExprExt
  case class LetRecExt(binds: List[LetBindExt], body: ExprExt) extends ExprExt

  case class LetBindExt(name: String, value: ExprExt)

  object ExprExt {
    val binOps = Set("+", "*", "-", "and", "or", "num=", "num<", "num>", "cons")
    val unOps = Set("-", "not", "force", "head", "tail", "is-nil")
    val reservedWords = binOps ++ unOps ++ Set("if", "lambda", "let", "true", "false", "nil", "letrec")
  }

  // desugared syntax
  sealed abstract class ExprC
  case class TrueC() extends ExprC
  case class FalseC() extends ExprC
  case class NumC(num: Int) extends ExprC
  case class PlusC(l: ExprC, r: ExprC) extends ExprC
  case class MultC(l: ExprC, r: ExprC) extends ExprC
  case class IfC(c: ExprC, t: ExprC, e: ExprC) extends ExprC
  case class EqNumC(l: ExprC, r: ExprC) extends ExprC
  case class LtC(l: ExprC, r: ExprC) extends ExprC
  case class AppC(f: ExprC, a: List[ExprC]) extends ExprC
  case class IdC(c: String) extends ExprC
  case class FdC(args: List[String], body: ExprC) extends ExprC
  case class LetRecC(binds: List[LetBindC], body: ExprC) extends ExprC
  case class ForceC(e: ExprC) extends ExprC
  case class NilC() extends ExprC
  case class HeadC(e: ExprC) extends ExprC
  case class TailC(e: ExprC) extends ExprC
  case class IsNilC(e: ExprC) extends ExprC

  case class LetBindC(name: String, value: ExprExt)

  // values
  sealed abstract class Value
  case class NumV(n: Int) extends Value
  case class BoolV(b: Boolean) extends Value

  import java.util.UUID.randomUUID // for generating a random ID and hash code

  case class ClosV(f: FdC, env: List[Bind]) extends Value {
    override def toString: String = "<closure>"
    override def hashCode(): Int = randomUUID().hashCode()
  }
  case class ThunkV(var value: Either[(ExprC, List[Bind]), Value]) extends Value {
    override def toString: String = "<thunk>"
    override def hashCode(): Int = randomUUID().hashCode()
  }
  case class ConsV(head: Value, tail: Value) extends Value
  case class NilV() extends Value

  // other
  case class Bind(name: String, value: Value)
  type Environment = List[Bind]

  // exceptions
  abstract class ParseException   extends RuntimeException
  abstract class DesugarException extends RuntimeException
  abstract class InterpException  extends RuntimeException
}
