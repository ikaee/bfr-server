import scalaz.\/

package object commonPredef {

  type Validate[A] = String \/ A

}
