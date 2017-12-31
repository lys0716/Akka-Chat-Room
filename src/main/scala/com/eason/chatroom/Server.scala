package com.eason.chatroom

import akka.actor.{Actor, ActorRef, Terminated}

class Server extends Actor{
  var clientList = List[(String, ActorRef)]()

  override def receive: Receive = {
    case Broadcast(msg: String) => {
      val userName = getUserName(sender)
      broadCast(NewMsg(userName, msg))
    }
    case Connect(userName) => {
      broadCast(Info(f"$userName%s joins the chat"))
      clientList = (userName, sender) :: clientList
      context.watch(sender)
    }
    case Terminated(client) => {
      val userName = getUserName(client)
      clientList = clientList.filter(_._2 != client)
      broadCast(Info(f"$userName%s left the chat"))
    }
  }

  def broadCast(msg: Msg): Unit = {
    clientList.foreach(x => x._2 ! msg)
  }

  def getUserName(actor: ActorRef): String = {
    clientList.filter(actor == _._2).head._1
  }
}
