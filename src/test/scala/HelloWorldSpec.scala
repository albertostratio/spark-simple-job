package org.stratio.sparksimplejob

import java.io.File

import org.apache.avro.Schema
import org.apache.avro.file.DataFileWriter
import org.apache.avro.generic.{GenericData, GenericDatumWriter, GenericRecord}
import org.scalatest.FunSpec

import scala.io.Source

class HelloWorldSpec extends FunSpec {
  describe("Adding 1 to 1") {
    it("should equals 2") {

      val schemaContent = Source.fromInputStream(getClass.getResourceAsStream("/rawModel.avsc")).getLines.mkString

      val schema = new Schema.Parser().parse(schemaContent)

      val rawModelGenericMadrid = new GenericData.Record(schema)
      rawModelGenericMadrid.put("order_id", "test_order_id")
      rawModelGenericMadrid.put("client_id", 2)
      rawModelGenericMadrid.put("latitude", 20.386d)
      rawModelGenericMadrid.put("longitude", -3.847d)
      rawModelGenericMadrid.put("payment_method", "credit card")
      rawModelGenericMadrid.put("credit_card", "2413412")
      rawModelGenericMadrid.put("shopping_center", "Madrid")
      rawModelGenericMadrid.put("employee", 1)
      rawModelGenericMadrid.put("total_amount", 20.14d)

      val rawModelGenericLeon = new GenericData.Record(schema)
      rawModelGenericLeon.put("order_id", "test_order_id")
      rawModelGenericLeon.put("client_id", 2)
      rawModelGenericLeon.put("latitude", 20.386d)
      rawModelGenericLeon.put("longitude", -3.847d)
      rawModelGenericLeon.put("payment_method", "credit card")
      rawModelGenericLeon.put("credit_card", "2413412")
      rawModelGenericLeon.put("shopping_center", "Leon")
      rawModelGenericLeon.put("employee", 1)
      rawModelGenericLeon.put("total_amount", 20.14d)

      val file = new File("/tmp/rawModel.avro")

      val datumWriter = new GenericDatumWriter[GenericRecord](schema)
      val dataFileWriter = new DataFileWriter[GenericRecord](datumWriter)
      dataFileWriter.create(schema, file)
      dataFileWriter.append(rawModelGenericMadrid)
      dataFileWriter.append(rawModelGenericLeon)
      dataFileWriter.close()
    }
  }
}

