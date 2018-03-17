/**
  * Version: Week 6: Lazy
  * Source: Weblab Assignment 6 2017-2018
  */
object Solution {

  import Library._
  import Untyped._
  import Parser._

  case class NotImplementedException() extends RuntimeException

  object Desugar {
    def desugar(e: ExprExt): ExprC = {
      throw NotImplementedException()
    }
  }

  object Interp {
    def interp(e: ExprC): Value = interp(e, Nil)

    def interp(e: ExprC, nv: Environment): Value = {
      throw NotImplementedException()
    }

    def strict(v: Value): Value = {
      throw NotImplementedException()
    }

    def force(v: Value): Value = {
      throw NotImplementedException()
    }
  }

}