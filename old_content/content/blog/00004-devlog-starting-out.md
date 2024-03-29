---
author: "Dave Smith"
date: 2018-11-30T00:00:00Z
title: "Devlog: Not quite starting from zero."
draft: true
tags: ["scala", "devlog"]
categories: ["Game Development", "Game Devlog"]
---

![img](/fppixels/images/fallAndStop2.gif)

I’ve had an idea for a game - as yet untitled - rattling around in my head for quite a while now, but attempts to actually build it have been a bit fruitless so far.

At first, that was because I couldn’t find a game engine I liked*. Later it was because, out of sheer frustration at the state of game dev tooling, I stopped to write my own game engine (Indigo).

Having recently finished [Snake](https://fppixels.tumblr.com/post/180236288296/snake-in-scalajs-this-was-a-milestone-week), I’m now at a point where I’m ready to pick up where I left off on the untitled game. The development of my little game will inevitably lead to me into being sidetracked onto missing engine features back in Indigo, and so progress will be slow.

Anyway, what I have so far can be seen in the placeholder-graphics-laden screenshot above. You can see the cursor too unfortunately, but that's due to the screen capture software.

Note that after the naked dude lands he wanders off to the right. This little behaviour is hard coded.

The plan eventually is to have the little guy walk around and do things in a fairly self directed way, which leads me to what I’m working on at the moment:

1. In order to have the naked guy do things (e.g. ...find some clothes**), he needs a list of jobs.
2. To manage a list of jobs, including finding or generating more work, I need a little job system of some kind.
3. I could just munge that into the game code, but I’ve noticed a pattern emerging that I’ve decided to encode into the engine which allows for the creation of little event driven subsystems that don’t require much explicit set up or management. This, I believe, would also allow me to clean up and standardise a number of other areas of Indigo’s implementation.

…and that’s what I’m doing now. See, I told you I’d end up working on Indigo instead of the game!

---

\* I can say that after quite a lot of reading around and trying things where possible: If I wasn’t a language snob, if I ever wanted to actually make a living as a commercial games developer, as a one man band, who is very much a programmer, who won't work on windows, refuses to use C++, thinks C# dev tools on a mac are still awful, and demands a statically typed language with functional niceties (lambda syntax, ADT’s, implicits, parametric types etc.) …I would, and indeed should be using [Haxe](https://haxe.org/).

** He’s not naked for any good reason.
