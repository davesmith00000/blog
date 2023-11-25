import laika.ast.Text
import laika.format.Markdown
import laika.config.SyntaxHighlighting
import laika.config.PrettyURLs
import laika.helium.Helium
import laika.theme.config.Color
import laika.theme.config.Color._
import laika.helium.config.ColorQuintet
import laika.helium.config._
import laika.ast.Path.Root
import laika.ast.Image
import laika.ast.Length
import laika.ast.LengthUnit
import com.comcast.ip4s._
import scala.concurrent.duration.DurationInt
import laika.sbt.LaikaPreviewConfig
import java.time.OffsetDateTime
import laika.ast.{Image, ExternalTarget, InternalTarget}

import sbtwelcome._

ThisBuild / scalaVersion := "3.3.1"

enablePlugins(LaikaPlugin, GhpagesPlugin)

Global / onChangedBuildSource := ReloadOnSourceChanges

Laika / sourceDirectories := Seq(
  baseDirectory.value / "docs"
)

lazy val commonSettings: Seq[sbt.Def.Setting[_]] = Seq(
  version      := "0.0.1",
  organization := "davesmith00000"
)

addCommandAlias(
  "publishBlog",
  List(
    "laikaSite",
    "makeSite",
    "ghpagesPushSite"
  ).mkString(";", ";", "")
)

addCommandAlias(
  "build",
  List(
    "laikaSite"
  ).mkString(";", ";", "")
)

addCommandAlias(
  "preview",
  List(
    "laikaSite",
    "laikaPreview"
  ).mkString(";", ";", "")
)

lazy val blog =
  project
    .in(file("."))
    .settings(
      neverPublish,
      commonSettings,
      name := "davesmith00000"
    )
    .settings(
      // Make site
      siteSourceDirectory      := target.value / "docs" / "site",
      makeSite / includeFilter := "*",
      makeSite / excludeFilter := ".DS_Store",
      git.remoteRepo           := "git@github.com:davesmith00000/blog.git",
      ghpagesNoJekyll          := true
    )
    .settings(
      laikaExtensions := Seq(Markdown.GitHubFlavor, SyntaxHighlighting, PrettyURLs),
      laikaTheme :=
        Helium.defaults.all
          .metadata(
            title = Some("davesmith00000"),
            description = Some("Dave posts things here from time to time."),
            identifier = None,
            authors = Seq("davesmith00000"),
            language = Some("en"),
            datePublished = Some(OffsetDateTime.now),
            version = Some("1.0.0")
          )
          // .all
          // .themeColors(
          //   primary = Color.hex("29016a"),
          //   secondary = Color.hex("9003c8"),
          //   primaryMedium = Color.hex("a888db"),
          //   primaryLight = Color.hex("e4e4e4"),
          //   text = Color.hex("5f5f5f"),
          //   background = Color.hex("ffffff"),
          //   bgGradient = (Color.hex("095269"), Color.hex("007c99"))
          // )
          // .site
          // .darkMode
          // .themeColors(
          //   primary = Color.hex("29016a"),
          //   secondary = Color.hex("9003c8"),
          //   primaryMedium = Color.hex("a888db"),
          //   primaryLight = Color.hex("e4e4e4"),
          //   text = Color.hex("5f5f5f"),
          //   background = Color.hex("ffffff"),
          //   bgGradient = (Color.hex("29016a"), Color.hex("ffffff"))
          // )
          // .all
          // .syntaxHighlightingColors(
          //   base = ColorQuintet(
          //     hex("2a3236"),
          //     hex("8c878e"),
          //     hex("b2adb4"),
          //     hex("bddcee"),
          //     hex("e8e8e8")
          //   ),
          //   wheel = ColorQuintet(
          //     hex("e28e93"),
          //     hex("ef9725"),
          //     hex("ffc66d"),
          //     hex("7fb971"),
          //     hex("4dbed4")
          //   )
          // )
          .site
          .topNavigationBar(
            navLinks = Seq(
              IconLink.external("https://github.com/davesmith00000", HeliumIcon.github),
              IconLink.external("https://twitter.com/davidjamessmith", HeliumIcon.twitter),
              IconLink.external("https://mastodon.gamedev.place/@davesmith00000", HeliumIcon.mastodon),
              IconLink.external("https://discord.gg/b5CD47g", HeliumIcon.chat)
            )
          )
          .site
          .internalCSS(Root / "css" / "custom.css")
          .site
          .favIcons(
            Favicon.internal(Root / "img" / "favicon.png")
          )
          .site
          .landingPage(
            logo = Some(Image(InternalTarget(Root / "img" / "davesmith00000.png"))),
            title = None,
            subtitle = None,
            latestReleases = Seq(),
            license = None,
            titleLinks = Seq(
              VersionMenu.create(unversionedLabel = "Getting Started"),
              LinkGroup.create(
                IconLink.external("https://github.com/davesmith00000", HeliumIcon.github),
                IconLink.external("https://twitter.com/davidjamessmith", HeliumIcon.twitter),
                IconLink.external("https://mastodon.gamedev.place/@davesmith00000", HeliumIcon.mastodon),
                IconLink.external("https://discord.gg/b5CD47g", HeliumIcon.chat)
              )
            ),
            documentationLinks = Seq(
              TextLink.internal(Root / "posts" / "welcome.md", "Welcome! ...are you lost?")
            ),
            projectLinks = Seq(
              TextLink.external("https://github.com/PurpleKingdomGames/indigo", "Indigo"),
              TextLink.external("https://github.com/PurpleKingdomGames/tyrian", "Tyrian"),
              TextLink.external("https://github.com/PurpleKingdomGames/ultraviolet", "Ultraviolet")
            ),
            teasers = Seq(
              Teaser("What is this?", "Sometimes you just need somewhere to put your screenshots and ramblings.")
            )
          )
          .build,
      laikaPreviewConfig :=
        LaikaPreviewConfig.defaults
          .withPort(port"8080")
    )
    .settings(
      logo :=
        s"""
      |Dave's Blog
      |""".stripMargin,
      usefulTasks := Seq(
        UsefulTask("build", "Rebuild the blog.").noAlias,
        UsefulTask("preview", "Preview the blog.").noAlias,
        UsefulTask("publishBlog", "Publish the blog.").noAlias
      ),
      logoColor := scala.Console.MAGENTA
    )

lazy val neverPublish = Seq(
  publish / skip      := true,
  publishLocal / skip := true
)
