import org.nuxeo.client.NuxeoClient
import org.nuxeo.client.objects.{Document, Documents}

import scala.collection.JavaConverters._

object NuxeoGateway {

  private val nuxeoClient = new NuxeoClient.Builder()
    .url(Config.nuxeoAddress)
    .authentication("Administrator", "Administrator")
    .connect()

  def createDocument(moreDocument: MoreDocument): Document = {
    var document = Document.createWithName(moreDocument.getName.toString, "File")
    document.setPropertyValue("dc:title", moreDocument.getTitle.toString)
    document = nuxeoClient.repository
      .createDocumentByPath(moreDocument.getPath.toString, document)

    document
  }


  def listAllDocuments(): Unit = {
    val docs: Documents = nuxeoClient.operation("Repository.Query")
      .param("query", "SELECT * FROM Document")
      .execute()

    for (doc <- docs.getDocuments.asScala)
      println(doc.getPath)
  }

  def shutDown(): Unit = {
    nuxeoClient.disconnect()
  }
}
