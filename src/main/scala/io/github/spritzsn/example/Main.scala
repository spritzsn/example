package io.github.spritzsn.example

import cps.*
import cps.monads.FutureAsyncMonad

import io.github.spritzsn.async.*
import io.github.spritzsn.spritz.Server
import io.github.spritzsn.json.JSON

@main def run(): Unit =
  Server { app =>
    app.use(JSON)
    app.get("/", (req, res) => res.send("hello"))
    app.post("/", (req, res) => res.send(req.body))
    app.listen(3000, "ExampleServer/1.0")
    println("listening")
  }
