import Library._
import Solution._
import Untyped._

/**
  * NOTE: you have to provide your own implementation for the parser
  */
object Parser {
  def parse(str: String): ExprExt = parse(Reader.read(str))

  def parse(sexpr: SExpr): ExprExt = {
    throw NotImplementedException()
  }

}
