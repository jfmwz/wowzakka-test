/*
 *  Copyright (c) 2011, Franz Bettag <franz@bett.ag>
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BETTAG SYSTEMS UG ''AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL BETTAG SYSTEMS UG BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package ag.bett.scala.wowzakka

import akka.actor._
import akka.kernel._
import akka.event.Logging
import akka.util._
import akka.util.duration._
import akka.remote._


class WowzAkkaKernel extends Bootable {
  val system = ActorSystem("WowzAkka")

  def startup = {
    val mgmt = system.actorOf(Props[WowzAkkaManagementActor], "mgmt")
    system.eventStream.subscribe(mgmt, classOf[RemoteLifeCycleEvent])
  }

  def shutdown = {
    system.shutdown()
  }

}


sealed trait WowzAkkaCommands
case class EatShit(foo: String) extends WowzAkkaCommands


class WowzAkkaManagementActor extends Actor {

  protected val logger = Logging(context.system, this)

  def receive = {
    case a: List[EatShit] => println("hmmmm"+a)
    case a: EatShit => logger.error("Gotten shit: %s".format(a))
    case a: WowzAkkaCommands => logger.error("Gotten a command!")
    case a: WowzAkkaClientAuth => logger.error("Returning true!"); sender ! true
    case "linkup" => logger.info("Successfully linked up")
    case a: String => logger.info("rcvd: " + a)
    //case _ => logger.error("unhandled")
  }

}


