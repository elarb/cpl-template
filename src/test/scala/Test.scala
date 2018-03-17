import org.scalatest.FunSuite
import Library._
import Untyped._
import Parser._

import Solution._
import Solution.Desugar._
import Solution.Interp._

class Test extends FunSuite {

  /**
    * Tests for Desugaring
    */
  test("Desugar 5") {
    assertResult(
      NumC(5)
    ) {
      desugar(NumExt(5))
    }
  }

  /**
    * Tests for Interpreting
    */
  test("Interp 5") {
    assertResult(
      NumV(5)
    ) {
      interp(NumC(5), List())
    }
  }

  test("Interp 5+true throws InterpException") {
    intercept[InterpException] {
      interp(PlusC(NumC(5), TrueC()), Nil)
    }
  }
}
