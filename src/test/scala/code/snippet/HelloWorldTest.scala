package code 
package snippet 

import net.liftweb._
import http._
import net.liftweb.util._
import net.liftweb.common._
import Helpers._
import lib._


object HelloWorldTestSpecs extends org.specs2.mutable.Specification {
  val session = new LiftSession("", randomString(20), Empty)
  val stableTime = now

  "HelloWorld Snippet" should {
    "Put the time in the node" in {
      S.initIfUninitted(session) {
        DependencyFactory.time.doWith(stableTime) {

          val hello = new HelloWorld
          Thread.sleep(1000) // make sure the time changes

          val str = hello.howdy(<span>Welcome to your Lift app at <span id="time">Time goes here</span></span>).toString

          str.indexOf(stableTime.toString) must be >= 0
          //str looks like:
          // <span>Welcome to your Lift app at <span id="time">Tue Sep 20 12:10:03 EDT 2011</span></span>
          str must contain (stableTime.toString)
        }
      }
    }
  }
}

