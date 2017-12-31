package com.eason.chatroom

import akka.actor.{Actor, ActorRef, PoisonPill}

class Client(val userName: String, server: ActorRef) extends Actor{
  server ! Connect(userName)

  override def receive: Receive = {
    case Send(msg) => server ! Broadcast(msg)
    case Disconnect => {
      self ! PoisonPill
    }
    case NewMsg(from, msg) => {
      println(f"[$userName%s's client] - $from%s:$msg%s")
    }
    case Info(msg) => {
      println(f"[$userName%s's client] - $msg%s")
    }
  }
}