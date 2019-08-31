![](https://git.qmx-software.com/open-source/minecraft/qmxmcstdlib/badges/master/pipeline.svg)
## QMXMCStdLib (for Minecraft 1.12.2)

This library is meant to offer an array of general-purpose data structures and algorithms for a multitude of uses within Minecraft. Each structure must be examined for its specific usefulness to be determined.

>Latest Builds: 
> * [CurseForge](https://www.curseforge.com/minecraft/mc-mods/qmxmcstdlib/files)
> * [QMXTech GitLab](https://git.qmx-software.com/open-source/minecraft/qmxmcstdlib/-/releases)
> * [QMXTech Artifactory](https://artifactory.qmx-software.com/minecraft-release/com/qmxtech/QMXMCStdLib/)
> * [GitHub](https://github.com/QMXTech/QMXMCStdLib/releases) 
<br>

Please note the current versions of this library are unstable. This project is currently undergoing a series of feature implementations in a manner whereby a feature is "incubated" until "mature", then a "release" version is made to tag such feature. It is recommended the public use these "release" versions until a proper release is made if they are interested in testing or utilizing current features (proper releases will start at version 1.0.0).

#### Known Issues:

* None at this time.

#### Recent Changes:

>2019-08-31 by Matthew J. Schultz (Korynkai):

* Fixed a light intensity issue when using Mirage and resolve use of deprecated methods. (OpenLights will need this, too).

>2019-08-30 by Robert M. Baker (Malachy):

* Fixed bug in LightHalo causing certain blocks to become invisible.
* Changed versioning system over to proper QMX versioning.

>2019-08-25 by Matthew J. Schultz (Korynkai):

* Colored, controllable, haloed lighting API added and completed, to be showcased in Cadmus Infrastructure; more to come soon...
* Many other changes made, mainly to build.gradle and .gitlab-ci.yml.
* Release 0.0.1 (beta of colored lights feature)

>2019-08-16 by Matthew J. Schultz (Korynkai):

* Initial Commit
