import $ivy.`com.goyeau::mill-scalafix:0.2.2`
import com.goyeau.mill.scalafix.ScalafixModule
import mill._, scalalib._, scalafmt._

val SpinalVersion = "1.8.0"

trait CommonSpinalModule extends ScalaModule with ScalafmtModule with ScalafixModule {
  def scalaVersion = "2.13.6"
  def scalacOptions = Seq("-unchecked", "-deprecation", "-feature")

  def ivyDeps = Agg(
    ivy"com.github.spinalhdl::spinalhdl-core:$SpinalVersion",
    ivy"com.github.spinalhdl::spinalhdl-lib:$SpinalVersion",
    ivy"com.github.spinalhdl::spinalhdl-sim:$SpinalVersion",
  )

  def scalacPluginIvyDeps = Agg(ivy"com.github.spinalhdl::spinalhdl-idsl-plugin:$SpinalVersion")

  def scalafixIvyDeps = Agg(ivy"com.github.liancheng::organize-imports:0.5.0")
}

object Examples extends CommonSpinalModule {
  object test extends Tests with TestModule.ScalaTest {
    def ivyDeps = Agg(ivy"org.scalatest::scalatest:3.2.9")
    def testOnly(args: String*) = T.command {
      super.runMain("org.scalatest.run", args: _*)
    }
  }
}
