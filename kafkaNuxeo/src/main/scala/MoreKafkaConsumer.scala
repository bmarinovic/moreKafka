import java.util.{Collections, Properties}

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}

import scala.collection.JavaConverters._

class MoreKafkaConsumer {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("group.id", "DocumentCreators")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer")
  props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer")
  props.put("schema.registry.url", "http://localhost:8081")
  props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true")

  val consumer = new KafkaConsumer[Long, MoreDocument](props)

  consumer.subscribe(Collections.singletonList(SharedConfig.documentCreationsTopic))


  def getNextRecords: Iterable[ConsumerRecord[Long, MoreDocument]] = {

    var records = consumer.poll(200)

    while (records.isEmpty)
      records = consumer.poll(200)

    records.records(SharedConfig.documentCreationsTopic).asScala
  }
}
