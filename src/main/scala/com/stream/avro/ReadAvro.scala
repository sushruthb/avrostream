package com.stream.avro


import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.avro.SchemaBuilder
import org.apache.log4j._

object ReadAvro {

  def main(args:Array[String]): Unit ={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark= SparkSession.builder()
      .appName("Spark-Avro")
      .getOrCreate()

    val data = Seq(("James ", "", "Smith", 2018, 1, "M", 3000),
      ("Michael ", "Rose", "", 2010, 3, "M", 4000),
      ("Robert ", "", "Williams", 2010, 3, "M", 4000),
      ("Maria ", "Anne", "Jones", 2005, 5, "F", 4000),
      ("Jen", "Mary", "Brown", 2010, 7, "", -1)
    )

    val columns = Seq("firstname", "middlename", "lastname", "dob_year",
      "dob_month", "gender", "salary")

    import spark.sqlContext.implicits._
    val df = data.toDF(columns: _*)

    df.write.format("avro")
      .mode(SaveMode.Overwrite)
      .save("/home/dev/person.avro")
    spark.read.format("avro").load("/home/dev/person.avro").show()

  }

}
