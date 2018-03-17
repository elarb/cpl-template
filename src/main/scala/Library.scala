import scala.util.parsing.combinator._

/**
  * NOTE: you don't have to make any changes here
  */
object Library {

  sealed abstract class SExpr
  case class SList(list: List[SExpr]) extends SExpr
  case class SSym(symbol: String) extends SExpr
  case class SNum(num: Int) extends SExpr
  case class SString(string: String) extends SExpr
  case class ReaderException(msg: String) extends RuntimeException

  object Reader extends JavaTokenParsers {

    def read(text: String): SExpr = {
      val result = parseAll(sexpr, text)
      result match {
        case Success(r, _) => r
        case Failure(msg, n) =>
          throw ReaderException(msg + " (input left: \"" + n.source.toString.drop(n.offset) + "\")")
        case Error(msg, n) =>
          throw ReaderException(msg + " (input left: \"" + n.source.toString.drop(n.offset) + "\")")
      }
    }

    def sexpr: Parser[SExpr] = num | string | slist | symbol

    def symbol: Parser[SExpr] = """[^)"\s]+""".r ^^ SSym

    def string: Parser[SExpr] =
      "\"" ~> """([^"\p{Cntrl}\\]|\\[\\'"bfnrt])*""".r <~ "\"" ^^ SString

    def slist: Parser[SExpr] = "(" ~> sexpr.* <~ ")" ^^ SList

    def num: Parser[SExpr] = wholeNumber ^^ { s => SNum(s.toInt) }
  }

  object Printer {
    def print(sexpr: SExpr): String = sexpr match {
      case SSym(str) => str
      case SNum(n) => n.toString
      case SList(l) => "(" ++ l.map(print).mkString(", ") ++ ")"
      case SString(s) => "\"" + s + "\""
    }
  }

}