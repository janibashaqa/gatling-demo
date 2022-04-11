package com.gatling.demo // optional package

import io.gatling.core.Predef._ // The required imports
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation { // The Class declaration

  object Search {

    val feeder = csv("search.csv").random

    val search = exec(
      http("HomePage")
        .get("/")
    ).pause(1)
      .feed(feeder)
      .exec(
        http("Search ComputerName")
          .get("/computers?f=${searchCriterion}")
          .check(css("a:contains('${searchComputerName}')", "href").saveAs("computerURL"))
      )
      .pause(1)
      .exec(
        http("Select")
          .get("${computerURL}")
          .check(status.is(200))
      )
      .pause(1)
  }

  object Browse {

    // repeat is a loop resolved at RUNTIME
    val browse = repeat(4, "i") { // Note how we force the counter name so we can reuse it
      exec(
        http("Page ${i}")
          .get("/computers?p=${i}")
      ).pause(1)
    }
  }

  object Edit {

    val edit = exec(
      http("NewComputerForm")
        .get("/computers/new")
    ).pause(1)
      .exec(
        http("NewComputerPost Request")
          .post("/computers")
          .formParam("name", "Testing Computer")
          .formParam("introduced", "2021-09-29")
          .formParam("discontinued", "")
          .formParam("company", "33")
      )
  }

  val httpProtocol = http // common configurations
    .baseUrl("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val users = scenario("Users").exec(Search.search, Browse.browse) //Scenario definitions
  val admins = scenario("Admins").exec(Search.search, Browse.browse, Edit.edit)

  setUp( //setup Scenario
    users.inject(rampUsers(10) during (30 seconds)), //Declaration to inject in Scenario
    admins.inject(rampUsers(2) during (30 seconds))
  ).protocols(httpProtocol)

}