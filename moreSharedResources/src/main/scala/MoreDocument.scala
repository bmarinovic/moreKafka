import scala.beans.BeanProperty

class MoreDocument(@BeanProperty var name: String,
                   @BeanProperty var title: String,
                   @BeanProperty var path: String) {

  override def toString: String =
    s"name=$name;title=$title;path=$path"
}
