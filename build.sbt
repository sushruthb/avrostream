
name := "avrostreaming"

version := "0.1"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.4.4",

  "org.apache.spark" %% "spark-avro" % "2.4.4",

  "com.sun.jersey" % "jersey-bundle" % "1.9.1"


)

resolvers += "confluent" at "http://packages.confluent.io/maven/"
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "MavenCentral" at "https://mvnrepository.com/"
resolvers += Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  //To add Kafka as source
  case "META-INF/services/org.apache.spark.sql.sources.DataSourceRegister" => MergeStrategy.concat
  case x => MergeStrategy.first
}