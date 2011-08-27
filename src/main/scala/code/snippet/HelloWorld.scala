package code 
package snippet 

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._
import javax.management.remote.rmi._RMIConnection_Stub
import net.liftweb.http.{FileParamHolder, RequestVar, SHtml, S}
import java.io.{File, FileOutputStream}
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.http.js.JsCmd

case class Login(name:String,password:String)

object loginobj extends RequestVar[Box[Login]](Empty)

class HelloWorld {
  object name extends RequestVar[String]("")
  object password extends RequestVar[String]("")


     def login(html:NodeSeq) = {
    def searchProcess() = {
      println("Processing login with name=%s and password=%s".format(name,password))
      println("Now redirecting to message.")
      //NOTE: First set this value since the lambda exp which will set this is called in next request
      val l = Full(Login(name,password))
      S.redirectTo("/message", () => loginobj.set(l))
    }

    bind("frm", html,
      "name" -> SHtml.text(name, name(_)),
      "password" -> SHtml.password(password, password(_)),
      "login" -> SHtml.submit("Submit", searchProcess)
    )
  }


  case class Name(name:String, id:String)
  def results(in:NodeSeq) = {
    val l = Name("Taj Mahal","1") :: Name("Red Fort","2") :: Nil
      //"rows" ->  l.flatMap(a => bind("r", chooseTemplate("tbl","rows",in),"name" -> a.name,"idn" -> a.id))
	bind("tbl", in, FuncBindParam("rows", ns => l.flatMap(a => bind("r", ns,
		"name" -> a.name, "idn"->a.id)))) 
    
  }

}

