package com.stream.hive
import java.io.File

import org.apache.spark.sql.{Row, SaveMode, SparkSession}
case class Record(key: Int, value: String)

object HiveRead {

  import org.apache.spark

  def main(args:Array[String]): Unit ={

    val warehouseLocation = new File("/apps/spark/warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._
    import spark.sql

    sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) USING hive")
    sql("LOAD DATA LOCAL INPATH 'src/main/resources/kv1.txt' INTO TABLE src")

    sql("SELECT * FROM src").show()
  }


}
