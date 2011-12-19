./sbt package

java -Xmx256M -Xms256M -Xss1M -XX:MaxPermSize=128M -XX:+UseParallelGC -XX:OnOutOfMemoryError="kill -9 %p" -cp /usr/local/akka/lib/scala-library.jar:/usr/local/akka/lib/akka/*:/usr/local/akka/config -Dakka.home=/usr/local/akka -Dakka.kernel.quiet=false akka.kernel.Main ag.bett.scala.wowzakka.WowzAkkaKernel


./sbt console
ag.bett.scala.wowzakka.WowzAkkaClient.doIt

