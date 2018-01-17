import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}

import scala.collection.JavaConverters._

class MoreKafkaConsumer {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("group.id", "DocumentCreators")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(Collections.singletonList(Config.documentCreationsTopic))


  def getNextRecords: Iterator[ConsumerRecord[String, String]] = {

    val records = consumer.poll(200)

    records.iterator().asScala
  }
}
