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
import akka.util._
import akka.util.duration._
import com.typesafe.config._


case class WowzAkkaClientAuth(id: String, name: String, user: String, pass: String, platform: String)

object WowzAkkaClient {

  implicit val timeout = Timeout(10 seconds)
  //implicit val timeout = Timeout(15 millis)

  private val conf = ConfigFactory.parseString("""
    akka {
      actor.provider = akka.remote.RemoteActorRefProvider
      cluster.nodename = "wowzer01"
    }
  """)
  val system = ActorSystem("WowzAkka", conf)
  val actor = system.actorFor("akka://WowzAkka@127.0.0.1:2552/user/mgmt")


  def doIt {
    val c = WowzAkkaClientAuth("1337","foo user","foo pass","foo sender","bla")
    println("c: %s".format(c))
    actor ! "before"
    actor ! c
    actor ! "after"
  }

}



