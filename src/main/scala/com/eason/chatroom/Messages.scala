package com.eason.chatroom

abstract class Msg

case class Disconnect(msg: String) extends Msg
case class Connect(msg: String) extends Msg
case class Broadcast(msg: String) extends Msg
case class Send(msg: String) extends Msg
case class Info(msg: String) extends Msg
case class NewMsg(from: String, msg: String) extends Msg

