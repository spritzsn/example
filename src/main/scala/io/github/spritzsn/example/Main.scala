package io.github.spritzsn.example

import io.github.spritzsn.spritz.{Request, Response, Server}
import io.github.spritzsn.body_parser.JSON

@main def run(): Unit =
  Server { app =>
    app.use(JSON())
    app.get("/", (req: Request, res: Response) => res.send("hello"))
    app.post("/", (req: Request, res: Response) => res.send(req.body))
    app.listen(3000, "ExampleServer/1.0")
    println("listening")
  }
