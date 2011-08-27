package code.snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._
import net.liftweb.http.{RequestVar, SHtml}
import javax.management.remote.rmi._RMIConnection_Stub
import net.liftweb.http.S

object Message {
  var name = ""

  def render(html:NodeSeq):NodeSeq = {

    loginobj.is match {
      case Full(l) =>
        val ll:Login = l
        name = ll.name
        println("name found is %s".format(name))
      case _ =>
        println("Invalid case")
  }

    bind("user",html,
    "name" -> Text(name)
    )
}

  def showmessage = "#message" #> name


}

