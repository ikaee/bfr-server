import com.microsoft.azure.documentdb.Document
import dao.DBConfigFactory
import dao.DBConfigFactory._
import play.api.libs.json.{JsValue, Json}

import scala.collection.JavaConverters._

object removeAllAttendance extends App {

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"attendance\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}

object removeAllThr extends App {

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"thr\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}

object removeAllHotCooked extends App {

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype in (\"hot-cooked\", \"attendance\",\"thr\")  and tyrion.schoolcode=\"27511150602\" and tyrion.studentcode=\"017\" and tyrion.datestamp = \"12-05-2018\" and tyrion.timestamp = \"11:10:03\"",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}

object removeAllRegistration extends App {

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where  tyrion.doctype=\"registration\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}

object removeAllImage extends App {

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where  tyrion.doctype=\"image\"  and tyrion.schoolcode=\"27511150602\" and tyrion.studentcode=\"017\" and tyrion.datestamp = \"12-05-2018\" and tyrion.timestamp = \"11:10:03\"",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}


object getAllRegistrationData extends App {
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"registration\" and tyrion.schoolcode=\"27511150601\" and tyrion.studentcode=\"046\"",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}


object getAllMasterListLogData extends App {
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"master-login\" ",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}


object getMasterLoginData extends App {
  val masterLog = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"image\" and " +
      " tyrion.schoolcode=\"27511150621\" and tyrion.datestamp=\"14-05-2018\" and tyrion.studentcode=\"oo1\"",
    null).getQueryIterable().asScala.toList

  println("master data ===>" + masterLog)
}

//and tyrion.lattitude="0.0" and tyrion.longitude="0.0"


object InsertData extends App {

  insertIntoDB(Json.obj(
    "image" -> "iVBORw0KGgoAAAANSUhEUgAAADwAAABQCAYAAABFyhZTAAAABHNCSVQICAgIfAhkiAAAIABJREFU\neJx0vFmM5dl93/c5y3+/a93aq6v37pnhrNyHEjeJI4qybC2J7MRKHDuJH5IoyALkIQkMeJCH5CVB\ngMQBhCBGAiOykcSyZYm2SEmkSHGb4ZBDDmfpnp7p7umurq696q7/9Sx5+BdpGEguUEDh3pdzzv93\nfst3+YudnT/xOogoqwWmrpnNJmRZlzDu4rykaWqiMGA6OSLQAUGY4rzHOcciX9AfrJCXJWmaoqRC\nSWiaGqUUr3/3Fe59/cvIqmZ8uqCYzNBSsqgdS2tD4gBCraibhtIJkkATxgl1McdUJd0spakqgjDC\nOYcUUOQLPCAQJFmHqqrw3iOU5vDgEOEhjFM8nigMEVpT1w1Rt4NSEmmsp24airJhsVgghKBuGpwT\nIBRCSBprESga47BeYBqDNZY07XB8dIptGpTSGO+xTgCCoih57849up0BabeHkBLvHNY5DI4oSYii\niLwoUUFIGGcYoC5zQICQGOfxUlIVJXVRUlUGh8QYS9PUOGcRQqC0xjQGrTVCSeJOhtSaqm7QQYT3\nDlFXzI+O0B6F1AGj0QoP7k+I4xBjwCOw1uK8I5ABeVGxtLyC82C8QwqJc56s08W4GoEEa1GRYjqe\nsfvoETevX2H32+9TzWc0dYVQEo+gNhapA5q6IMk6iLTL/tGEx4+O6KUJdVEiBHgzJlSOLI4IpEAH\nGikc3hvSSFPmOTIIkFLTOJBKYYxhPp8R6pCqbugpTRyn5PMZSimkDkOqylDXDc5DXTfoIKSsSpxp\n8N5TVyWd/gBjLFVZwfl3dV1T1TVZmlLXNUKAMTVxFPH8Cy+wsrFOUdWAoCxLhNIgNV4pQCCDEI9g\nfnLMUrfHwfEYnaaYrMNr7+8RDJYog4z7k4Y3dmf8yWs7/OOvf4CTETqIcbTR2DSG2hjiLCPpZAgp\nOZ7XNCLi4aN9xrMFIkzQWQ/d1FW7GB8RKNUu2lokFmMaAGbTM7q9AeBRSlGXBqUkcZLxtW+8whe/\n+HmkkLz11tt8+CPPkaQxzhge3LtHlKQEYYRnH4RAhyHKWIy1qEBTzBeEQUBelmyuLzEYxvSCIbG2\nbC+nFIVic7WD0gHIgEc7j8mylMZUICQCsMZSlSWhzphO5wxW1oizjD/79hvY2vHUjS0wCzBjZFkW\neFsjhOMbf/7nFEWBsw22qfHeY01DJ+sgAYmgrmqUlCilqeuaFz/5cbz3eG959tlncM7RlBXz+YKN\ntVWMsRR1jQesc1hraZwjSlOsBx1oRKCxzoKHSEpktQDbgDUkUYBrDIGSxHFIHEnKMsd58A6c8+3K\nhGAxnZGkXcLOgNl8xuZqj5//yFX6iWCpH6MDgfZ4oihib2+fz/3iFyiKOU1dgTftxa9K4iikKnK8\nEAghQYcUZUmcZvR6fYIgwNoGhMc0FtNUdDodZBRjjCHVMXiJjhJqaxE6oikrAukwHpzxVA6iNEEC\nwnqWel2EB2Nse63qhlAoFuMJ6501jHMEQZtUdRjjplPiMKSxDVIKZpMpvSQgDR1RN0UpTSAMsmks\noFheWSOM0zZE6oq6ysE7hPDUVUFdV+BBCkldV3SyDLygqirqusRaS1lUxEmGcR48XNjexlmPA7yQ\nCB2QN57GeKazOU1tKCrB/rjhwf6MWWnJK8N4XlHZgA/2zjge51RGEIQpi0VNtz+kNOCFACSoECEV\nUii8AykVHkVV1iz3MqR3ZFGEryu0s+g4TimrEiEl0/EEYyyuKdsQXFi00ljn8F6AENTGkGUdmsZh\nkfzge6/x+c/9HN47vLdY0yC85+DggNe/9V1klPLo4IzT0lMXOe9+sMdkUXL98kUe7R9wOitZWhpy\ncDbDOs+33npIv9fj6GxCrBVYRxgIUml57kPXefP2LsNQ8+Lz1zk+OmVldZnalHgPZVURdroEYchw\nOKCTJkhXUZUlzjqUlojvv/7/eKUESkm88yxmYxbTM7RWNE1NGMZIHYBQICRR3CEMI6xzgCTNOoRa\nUjdNm73ritOTY+7cfo9vfvUvkEXN7Ts7zGpHXjVYoLEOYwzTeUmWJQhAKkmbgtqSiHAoKQnDkDiK\nODw8JAojrHVoV3F9vU83SbHlgkurXUaJxpqapNens7rO7Tv32Vrp402FEo75bIGSCo3wVFWNEAKt\nJHmxwOHxeKqqwlqHCiKiKGE2X7CRDWhMg5aCvMw5PTlmPp+ztXWBt9+8xT/9/T/EO8H77z8knzUI\nqXDOU9Y1nW4XmgbXNKRZRpxmONd2RD8tf9Ya0iTGOYfSur0yQhDFadsTxDHzec2PHp6h5BQtHPeO\n5yylAb1Y0U8WrJQeEYTnTUtBmiY4D1qARgjiJMUaS10VSCEp6xrhNY0xCCHaZqFqUEFIvpgD0CiB\nkprFouA73/o+33/ld3n88ADvA6raEicpjWvAO8IwpBu17aFWCiU1RVW2m5IK7z1lVbVPQAmKsv0t\njiKkEEynM9bW1hlPzrCmoWk866vLlFXbExzmObmAsKjZ9JriYEwUamRZMBrEeCRNY5EIdBC0p2tM\nQ5ImTM5OQbStYbEosKEDKpK0Q5kXxHGHuq6pq4KqMvzZn32HP/uT1yjyksFwmcHSCNMYDo+O2b58\nhbOzM6bTKd1Ol3w+b+spgNBI5ZjNZ2RZhtKKJEmoqxqPpJwvKMs5YahJ04THe7sEWhNFEZ0s5uDw\nmCSOCALNcDjk5PSMLE24e1qgxwWBs1wfpfR665iiJAxCBB7dVCXOWUzToJRkvpgSCigXcyKt8N6i\ntabMFygV0dQNi3kJzvPHX/4G3/3em8zymg89/RGSJGXv8IRO1uNifwXvLJvdActVyf7+Iavb15BS\n0TQlk5MT9nZ3WF1dRilJGISkaYroCvBQdAvyIsdaizUGD8RJSp4vSOIYKTO8921p8o5etwNAUdeE\nWUJVVTyYNSyflmwM2xpsqgJtjEFKgXOGKp8zGi6xe/8OeAthhBCSqjF4Ic5Dw3HnzkP+t//1H7Ky\nfo31S89zvd/n9ddf49Klq1x94mmKsuTw5ISqrAiUII37EMyYzHM6WcrJySnlYsH25Uv0s5TxZIL3\nkOc5CKiqmiiOyfMCayxhGKB0wGy+QIp2UvPe0+/1mM/n6DjEmLb2F2XJdJYz7HdwUvHW4xkI2F7K\nkNohXv3+7/npbEpzXlun4zNuvfkjsjigNxgghKKqG7yQSJVwfGr4F1/7McvrV/Ai5PD4hPl0gjUN\nRZmzuraO97STUD7n7HCP/b3HjEbLWOcQUpHEMUuDAVo6DvYfc3R0RL83oJMlnJyecHBwxMrqCoPB\ngHw+4/DwCO8sTdMQxRGjpSGz2YwgCMjSlPF4TBSGGGvBe7z3NE1DksR4axilIR+7tESmLDpfzAi0\nwlSOpq548MED1lbWmJwdMptOUUrjvEcFKWXt+OGbu5Q2YWfvEKQmjNoomC/mGGsRUnG4t0+ShExO\nj8iSCCng8PCAMO5w86kP4azlg0cP0cJSlQUXr1xjPp0ync04PDii1+uztDTi7OyUsipZ39pkOp7g\nTENZlTTGYp1HeU9Vtlm4aQzOOUZLQyaTKb1ej8l0ShiElCLgzb0FTy6nqN/+6194uS4K8nxOVZYM\nhwMmZ0fk0ynGGJAKKTS7Bwu++rVbPNqfMl/M0WGE1poiz8nzBZevXAMhcd6T5wvCQKOV4mB/H6kU\nOkrYvnwVIRWNMayurRNHMcY68kVOmibM85woTkizlIO9x6RJysHjPbz3dDoZnU4H7z1hGDKbzfHe\ns7q8jJCC8XhM1ukgBGSd9j53Oh263S5FWZH0elRFgcynU0ItacoKBYxPx1SVo24cVVFhG8PO4xPe\nvXPI2XiBMzVKCKp8we7OQ1ZXVlA6pLGW/nBA09TM5+1hTaZT6rJCByHeWu6+d4fj4yPmswn33rvD\n4cEeAuj3++w+2uXk+AQhIQoDgjDk5PSEbq9HYwzd3oC8KKmNYbg0IowToighz0smkylpJ6NuGoRQ\nJHFCJ8vaQcYYojQjjFPmKkFOpzNOTyfEccbe3jFBmFI24EVIURrG4xwhY27fvk9dFljbsP/4EYvJ\nhLXlZebzOZsXtphOJzz84D6333oLKTTT2YL5bMFwZZXj4xOiKObpZ59nMBhim4Y41KysruK9Z39/\nn36/h9KSOAyYz2YoJQFHbSqcdVhjODs7Y9AfcHo25uKlK9SNJU4Tuv0+vV6bb67duIkMQ2pjqBrL\nxtY2YRjRWEeY9dFRmFKWNdNpTtbtUVYOYxXHZzm9LEYHIa++eptur890XnL79i02trZZWhq2d3Nv\nF0/bHGxeusKTH3qOO7ffpshztrYvkxc5z37kE9x97w6z2QzTVNy/8w69pRHOO0xTE8cRhwd7JGnG\n0fEpYaAYDHrMpjN63R7WeeqmxvkWIprN52xfusr61iZSwKDT5WwyJe12SXs9ZBCwt/uIVAToMGaw\nsspg0Mcaj3rpF154OQhCyqoGoXn71vvkeUVTFzhrqBuP1Cm33n3IydmY0WjE1tYWj3cfsVhMscbS\n7fUZra2z83AH8Bzu79LUNZ1en9XNTYSQXH/iSeaLBVmnS9rpkqQZRb5gtLLC++/eoTcYYq1jbX2D\ng73HJElGtz8gDCOiKCIKI5RSzGczur0ejx49ojcYsrq2TmUsOozZ2NxibW2doqzbkdU5KieYz3M2\nLlxkaWUVGQQhCMl8VnB6OqPfX2KwtEzaXcahEULz7e+8RlU3jEZLpEnMwf5j8BaJp9tJ2HlwnyTU\nDPsZt976Mf1+j63tbXq9DuPTU4QQ/ORHr6OUZDgcUpc1cZIwK0qKsmHz0iWy/pCLV27gPKggorYw\nGK0SdbpEnR5Jt8/Nm0+itOYjH/0og36Ps7MzTicLXvqVX+PKzacojONoPKXxYND0lpbpDYa88LGP\n0zjfoiyf+vgTL3/w4DFStThRkmU47ynKCgAdBBwfzimqhvFk0gJlTdtjl2VJoBRHB3scHexRlRVX\nrl7HNg2nJy1kGscR9+++h1SSXtbh6PCI0eoKtbGMlldIO13u33mP0cYWUkmKPKdpDE899wJeKNa2\nLhDGGUVZobTCe9BByIMHD1haGpH0hjTOk3U7CAQXL11h88I2SinW1tYRSrGytk5RFERRhBytbTFY\nXsUicN7zyquvcnx0irECREBVGx7sPGKxWKCkpMxzvDVgGvLZmLvv3iKNAiIl2d7c4PDxQxbTM/qD\nIUoFVFXF0soa3eESg9VV4k6GkJowjBgMhxwfH/G5X/4SQRQjdMDWpatsXLqCRWI8CB1RWc/ahYsc\nnpzxzAsfodMf8flf/CWSTg8nJPM8p9cfcuHiZc4mE+K4nY6ybpdAh2At4EnSFPXSL734stIB89mc\nyXRGJ+uQJTFJouhmCY1x/PjHtyjLhjgKUUrRlAWTyZhIKYbdjH63x/rKMk0+YXtrg+5ghI4SDo+O\nWV1bI+v0iOPkZ5j33XfvYE2N8BbvDAf7u1y4sA3OUjcNvW6PYj7l0vYFwjAi63ZJk5StzS32D45Y\n21jnjbdvM1xaoTGWza0t9h4/Jk0SpBCURc5oaYAOQnYePUIK6HYztJKoK1e2Xx5PJljn8UBjLFVd\ns5jn7B8e0e8PuHf3AUGY4JxHCjBVSRaFLCWai1sbdAPBpVHK+sqIo/Gc8bygKksuXdpmMZ+ymE+J\ntGox77JiabREpBXz8QmRtMRBAK7h1ps/4uKlS9SLKScP30eYirKxXLy4jfQG4S3bm5scHR2xvrnN\nydkZURgQaUWaRPT7XR7c/wBvG2yTowXk85xBv4PE4ZxBvfjih1/2zp7Dsw4daJSUCKWIohhjLWvr\nG+ztHZImHbSEwDVsDLpsLPUoJse8cH2NpaUV3rx9F+ehqXLy8SnUJc18TKo8y0sD7r97i0g6RJOz\n1InoRApZzlnpxmQBrCyv0s1ShrHk2lqf1WGHvKxYSRVd7WgWU6ZnJ7z2/Ve5ev0GzlvSJGExPSXQ\nkvl0Sl0seHD3NhvLIw4P9njnzTdIooBur4MOItSHn3rhZRlDlMTtnStLmrqFYqWUxHFCkiYcHZ2Q\n5yXCOQYR3NhewyzG/PpLP8dyv8s777zNUjcjsDmfvLrKCxdH3Nzo0aPkxuYKuzsP6FFyuadZ72gG\nquH6Wo9nL69zdX2INQ15PmclC/nwxRE3tpc5OZvyq7/wItdWMtZ7KbPxCThPfzji5OSEldVVFtMJ\nsfLEWrG3c5/NtRUe3X0P25QIKQh0QBjHzOY5vZUL6FF3E9Od4JwlL2foIESGId65cxB+ynBphY2N\ndU6O3iUNAy6tDljqKJ66+ASJdvS6Xf71L32eeZ4zGg6IopgwjhiOltl59Jjbt+/xyWc+S3/YJc1S\nGmPJkozpbIb38I1vfJP1NEXVAZ949ir9XszK6hovfvITSCkJgoCHD3e4ubnMew8esWjmPNw/IjwP\n5+VeRn52jMzPqKcpq0s9siyhqGryquLCaIO6MURJhi6mNWFPEkUaZ2qEFCilcNbinEWrgLquCIO2\n/HzqMx/mQtewsTxk1B+SxjFaKQbDPheTTYx1JEkKQuKd5eaNa1y6uE0UZ8RZRlGVKNVCqut+jdPj\nY37jL/8yx0cnJJ0ul25cI00SJuMxYZzQ6fWoa8PW1jqmnIEZMT98jCxn5ONDLmxscnkYInsDtvqK\nt+89YmV5yP2Hu2TL62xde4pGaOJuhyCM0AqJaQrquq2t1jrqxrSL8oI4jpFCsHVhnUB6riwrlrOQ\nlX6GViAFBFrT1A1R7AgDTdPUdHo98C0BlnSzFq9uLGHUBaFwzlLXNVduXGcxm3LjqaeojaG2higO\nGa2MAEEcRzhrKRZTlgZd9h7e48Jyxjd/eAtrSnIz4/mf+xKBHLCzfwI4Hh1NuHz1Km/f3WHj5gt0\nBkO8cyzyOVo2MUVZ4aUnSTLSKMI5S5ZmeO+YTCYoFRCEmum0RglHoCPqqkSnumXkggDroGkModQE\nQUAYBpRVw+nJMWmnQ1mUCCGJohghNUIqlFKgJL2lJZxrDytwDmMNYRDiPdRVSZqEPPnUkzRVwcrq\nEqaq+Uu/+WvoQNEfdIjjgNPTM3YefsClpZh7996nDCwfevYFvBfs7uywtrqK8AbdVA4dROhQtfSo\nc4CgsTWL+eJ8UYI0TFkedQCNFII0TUnTDKkUAkkURVjXUiNxmmCcJU5ilA6wxlG7miiK2o1LSZym\nKK1bJFrQoqPWIpVEKfWz34QQWNfSLTpK2Lx4BXAtju4NQdDW3SjW/I1/+69y69a77B0f88q7R2Sb\nHtss2FxdaSmkwCOLvELg8N6htcYYgzENi0WBUhqtAkLddkwvfuoF0k4Hax1NY2jqBtPY9t7roKVb\njWNRFC1KEkZIpUiyDB1oPBCGIc5agiBAAM4YivkcU7dMpZQSHQQEuoV1rTVYazDW4PBtUpUapMA7\n1zKfUrRrtxWrayO++LlPMVAlg0QSB5I4lNhyzvzwAeoTN37+ZYYT8nxOrz9oAXndLgbXMgkgcN6y\nuTQgFRbRFAy7vRZRGAxRQQhaI4MQFURMJlPqxjBaXgYExjTESYLxljiOCVSAdRahJNYa4iRGSn5G\n66RpQlWVeOcoFjlSBigVAJKqrvDeUZcFCN9GhnN45zDWEacJG1vr4BoWpSdvBEKCMA2Lo3to6yzD\n7pDEVjjTPi2cR3gIQo0QHmMbrGno+4YgTEgjTTpaZdE4vJM0pmKl30cHIf1ej7WtVXQQYHHEacz4\nZIGzAZ0soyornPV0Bz2MtWilKIsCKRXON/SGI2Z5RZb1OD09IemNSLIOUbeLbUowBlMXeJ+hlceZ\nCmdrqrLEeEUn7WKs4QtffInkjYd87c1HWOcI8EhboL1oMKY5Z+Now8i1bMFPQTlb1dRNw5mUPLh7\nlyyUHIxzwjAkTVKUUsyKNrtmyTEXNjdQoSRNM5qyIgoVi9kpUvSwtSEIQ+bjE5JOC+rrICQMQ5AB\nOgwYdjo471leW0dHIfP5Al8ucM4TJxFKSoIkgqakKaGYFqAjemkfh0RJz/Jql6wzpd9vJz3tp7z4\nhc+gVUQrSxAtTxyGAUK2d1SI9gBkEBJrjQQ2N1cIXU0USHQY4qXAyIAfv/sBDx7tE2rN0zeucmV7\nRD9LaKqc0bBHPptSzjsopds7HUbMpyWmMeR5zWB5GbTi5OgRQivCKMF6Sdk4ZvOCuq4x1rXsf12x\ntr7KhQsXzg8paXOPl3haytQJwc7hGUIo3PFD/tO//mtsXb6A9pEDKUmSBLA01p1zeAJoEUIhBFJK\nTF0RBwFLaUa318c6ODwdU9YlSZxw7co2i+mEe++/y1uvn6GE5/q1y1y7col8MmFzY8hg0Gc2GTNa\nW6EoCuqq5uHOIesXLiCDAC8kR0dnvHv3AyaLGhFlDIcjrly+QL/XZb03YPfRAx58sMP+3iFBENBU\nBc8/96FWziQEVV0RhDFWhCSq4N/7d/4aN557GlfP0eGgpSrxIGWAtYYg0uR5Ac6DkCgp2rKgNRtr\nqxw8uM+3v/cTvvnabY4XjmeevMza9RvgBZ/43F/m3vvv8Qd//Lv87d/4OUajFcanZ+AayqLC96C/\nNEQIibW2PVjveefNd8jLksYKkIokTdFKESYxUDA9eQx1l3GmefKJa4Di7OyUQElOmgWPHu9y6dJF\nrHVIKSkqR103/M5ffYn6eI9vfPX3eXNvF13IGapstVXet8KJsioIw4Ckk+GdbzUfzuKAIBU8feMS\n6yurfPELn2V5ZZmmdnzju99n7+CY73/5HzLNKy5sjnjyU79EMH2MNwsCb5EqJOl2Wd7cIohiOsWC\n+WRKYwRp2mUyz3m4s8/p2Smv3X2T24clK1sb3H7nHp968eNMHrzJR5+7ya++9BmeuHqRbr9L0snY\n2FxhschxzrblVSnOxjOe3chIyz2m9Zw/+Na3ufzEk2gnLFoE2HNqNAgVUobtBp3HGoMUAq00VVni\nnSbNArI0xnrF2voyOkrY2FxGyYDDwxMKI1hdWUH7isODgmvXPsI7r3+fKNSEcUpveYO4v0Sdz4nS\nYwwxi2qPa1tXKG1AwRG/9W99nr/45rd5/fU3yah49O5bXN5aoSg8/+3/+A+IfM7Pf/QpPv/S57n2\nxE0uX7lK3ZQkUURdVeQnh7z1T/4BG7/+Jf7L3/1HpGsbjC4ZtPct2ZzEEdY0WGcACHSElBod/xTD\nkkRxhpUNgdasrK+DCJktppRnJ6RZRhQoBonEnU659+YDZvM5nW7KvnaM+glhGLC5fYl4dAGZDYnT\nBVJGNDZBRz3GB7s899QNPvb803xw/wG/8xtfIP2bv4GIM5ZW15BKMVpeRqhWzOJMQRQFBFEI0sPc\n45ynbiybK2t85NOf44nPfonL33mXezsPuXXrLhoBnhbR63Q7LObz8/uqsNYAivliwTRdIXjiw1w8\nexvnThHek5dzti9dpKkM+/uHPLx3j/HZGY1xbGxt8ZELLzCfHOKKOVXd0B8M0WEEIoAwAevQSZ/e\nSNNbXidJOnz9y39ApDWdTocs0aSxRIWOXgxpv49X0Jia3qCPNRIdB4DDm5q6qdBSEQcRP377B/zi\nv/8f8J//13+XztKI51fXmIynaCkl3hoab5hOaqTUeA/GGKx1GFNwfHzC/s2nkGc514IBdb7HdDJh\nfXsbpQM6w2WWL1yiqQ1NWVAWJXkxx5Q5oXCUpmJp2KXTyVBBhIwzUCFEFokjVhpQZI3jt/72f8T9\nt3/MK9/4BkVZsLm9xWBlhapqCI0jCDT94QC0RCcRONPKKKoSbx3GB4go5cmf/zT/2d95mbA/AO+p\nioI4idBFkRMF7SgYBBqtFVK2pJgFutmAsqrZe3iXybrly2e7fOyyptMdECYdgihBSI13Hm8d+XyB\n84ZQK8pyQTmfoiRY2xB3uqi4C0HUqsq8B6lRSYrJS0YXtrFFwc2P/RyXn3oGUxVIPDoKSZIUOVyi\nLbTnVdM1UBfQVJT5Ams849mYte0uf/d/+J+JekOkEBRFQRgErcIwDBOcF8RxipQB1bk20lmLQtE0\nDStra1xP4d3/639nMwxRYUoQBudiT49rasr5jKaYE2qBFmCqgqauqI1BhQFRmhJ3ugjZ6ixZtEQ1\ntoEwRApwdYmKIqJen87qFr3lDWSYtBqvOKalBnsQJKAkZnqGyceYco4AjJfEWcZkXjJcv4A/T7pK\nKqq6xhjTKgDmpqRuKtJzKHU+myGVQkpPUxo6QtLvJPyHv/2v8Xj3MQu73HY+ZYEzzbn0T7Ztqakp\n5nOmsynz+ZyiKOn3OmxdvkqUdGgaiwpTCGJwNXR7MD5BJgHl6TFh2sP7VkoopCKMIpq6xJQVwWAZ\ndAw6Bd+g+zUszjDFnLyoQMQMs5TXbt+nKCuyThchWjWSahqqpkarQJFECdaa89kWhPQs5lOEUCgd\n4L1D4sFUDCn54Q9eZ633SeKkZSqapkFKhTUN08mMujFMJ2dsb66yU+Usr2/iiJjNHcOr10GmQBuO\n1cEjwjSFqiDKYo73HhIFAZ1On6axtEILwBtYTNtwDmKwJUgJ1jGdLkB2+PJX/4KTyYx3Hx8QJhle\nVIRBAEKig4gwTtBaR0jZpnl/PgYe7x0QRSGdTo8gCKnqGiklo37K/t07JP2Ys/GCOI6pqxqtNVVV\nc+/uDg5YXV0miUKSLCOIM2QYEfaWSbMhYIGWxiFQGOOJCKDfx54dsHLhAscPPuCDx7t0sy694RAh\nHK722PkYpQXUOfgavKEqcqrKoLIOr79xRGENK+uXCGJFZUq898RxRFWVNI1BTycFWhqE/OkaBP1+\nvxWrobDG01Q5g0Gfd777LS6tDEiiiO+//jY//8nnCYO2RTw8noAI6A97zBcLlHI4ERKlHSbTOTo9\nIxEefdQQ9JfahCU0WacLvQFIj0piitMxUmkO9494lH/AxvoqS6MhQRyiVBd31uBsjVQCoSSnR0dk\nS1v8nf/mfyFNL7A2HPBoZ4dFPufo9ISiyfnkix+n21sjVAXqxQ9dfLnckoI6AAAQtUlEQVScnrZP\nIwzxtMo7rUN0EOBEwPv393m8e8QmM1aHfcIgRKrgXGIvsc4TRhFhEqN0eK7CNcxmC2rjuXd/h/lk\njneWNAqw+RQzm+DyOQKLtCWunHG0+5hOb4RzLb5WLgr+8J/8M7I4QjiD9BYpLc421FXBdDxFhRmF\nCfgv/qv/nk9/7iWsF3z1q1/h+eefI4wiXn/9DdbXtpFW896799H3b7/L+qDLxrDPeHqG7a8wWzj2\nD8Z8+7uvE6V9RitrpPMP+NQnL7cAnQporGVnd59OJ2I07NIYR54XpL2ATtzFecWirAgDSac3xHnF\nwwePOTodM5tMCKKIOB0SJAmrW1uEUUrSGVIUNY2TxN0e6aDLl/7Kr/CT137AfHrGxmKd/tIQFQYI\nqRBhSpqm/PM/+jbLK6uMJzMePd5jOl+QZQlvvX2LjY0tRqNlVldW+L1/9Htou3yBx87xnW/8hEak\nWHUCUmOdYLh6ERDMZ3M++8Qmcdy2h1IoXNMgpeaNn7zHZz/7UbJuh8YvOD0dA4pYa/qdLqaqSZIO\nQggaBJtbl7BrNY93H5N1hgxWVun3lwmS1oyhlCCOQx7v3CftpNg65/mPvcBsPMadc1+NBx1pkiDk\na996g8FojaZqcF5w84knODzapzaez3z2s/zzr/wxs9mMbhyQRiWqdvrl+ztH5DZiuiixrhWbNk2D\nEJI8X0Bd8NIzqwy7aat4Pcevx5MJSoe8885trly7gpKCPC/5wQ/e5v79I9760du4yiKNJwoiVkYr\nbKxvkoQJm+tbDLpDulkXpTRNXpCfjjnb32d6esr45LgtKXWruF1ZX6XT65+PrzFh0uW7P7yL1H3K\nvOL4rKF2UDU13W6HOGkp0yCI2NnZRbqav/ff/cfog8MxQkpUIDBVSRRFLc0ioMjnOOsIzIw0VGil\nALDG4JxhkS+QskapgD//+nd45vnn0Fpy4+omziVcvfgFDt97n3dfe4uT6YS/9rf+XV752qu89dZb\n/MKv/ipGJ3zz619nfLBHJARr25e58uRNkl5AbcacHc+5cnmbJE0Jw5DpZErjBWfjmts/vkOSdJHe\n8tprr7O+dQmL5NVXX+Hy5W2MsZyenrE0GhHogOtXbiCRKBmkLyspsM4jtUYrjQ40eNfiTVqzFFR8\n+pmLZEkCCPKy4PT0DKkkZ6dnWGPo94f8vd/7ClevX2dt1GExPeGtN9/gwx/+CJEO+OjzH6aeLQiE\n4sr2Zfy84Pj+Dhura/ziX/orfOnf+G3WL65hmLC0mpAmIcobPrh7h6qsmUzmHJ/MeHQ453ihqI0l\nwNPUNX////inbF996mdq38l0zNMfeoY33vgJly5dYqnX5d/89Y8SqAYhkp5vkXAFSLJeD+9FC+TF\nCWEY8ZFVx29++kPEgaYsK4qy5ODojPsf7LKzu8+Nq5eJOgN++GDK3v4e/8nf/BUujWLyec70aMpz\nT77Ak89+jOXtK3ghqeuG3soa0/mc3qjPndf+nMe79+kuZXSHXaoyp8hz5rMp3nnq2jOZ5pSloxEa\nr0O6cYRtGj7YOeKVt4/J+iOkUpweHxEGmiiKOTw8pNft8NyT2/yN3/w4zlYoEC/jbPt0w4Cs00EG\nIUtLSwRhQDWf8Nmby6wOM5KolRE2Vd16lkrDeFHyk9sPkUmP3bMpSZKQG8Wol7A6SukOUh7s3mO8\nt0tQLShPDxjv3mX88D2mhx9w6yevcDQ7ZnlrFesapJJ4a7HWY6zkRz96m8PDU4qiZp4vUFIQaUms\nJTjH//mPv8ba5Sd46tlnuXXrFsdHh9y4eZNXXn2Vmzdvsrm+wm/80sfw9ZQojFqBeH9pCesEYZwQ\nKM3p4RFnu4+RYch6JlkbZsRRhFYabw1SglQQBIJOEhEkIY9PpywWC7IsY7qo+J/+/v/N3/2d32I4\n7PH0s0/R1IZb++9SFgZjHVGaMFpfZW1rneVQkSYJgpi6bsibmq9+9WscHp6SZRmB0uggYDgcEWpJ\ngEN4x92He3RWr1Abx6PdxwSBZGk0oqprhIRHjx6xOrhGIHKssRRFgY7SmKooKOcL8ALCALwEHeBM\nyZc+9hyDXocoDFFKYIRHK3k+SkqEFJSmVZsrIYjCthUd9nucnU6Zjmd0nuuhA8nKap8062I9JFnG\ncHmE0u1Ims9zirLk9q07/Is/+grDpREbK6NWWZQmpHFCvz9soeQoovGeP/3WO6jBFssbG3z9a3/C\n6tISly5e5Dvf+RY3r18nDAM+84mnqPM53nlM06Cr6Yy2rxQtTGIahBLceOoq292Q7eUegdIgaM1U\nArzwrarVOWrjKBpH5AVhGCGAfFEgnQfnqJuG733reyyPhiyNhnS6cwajEUEYMjk5/Zmv4fY7t7j/\n/j16WY+PPfccKghI0gwdRHS7PcIoJk4TpArwwB/96fcYbF7nhRdf5P2799jaXKfX6XJyego4JvMZ\nz9y4wlLmqesSicQZ34Z0O1UbRhsbXL95nSAMiQPBtpvT76V0shTvQKjWxlPVNU1jKOqaojEsrMdM\nJ2RhhDUWqRoOD05pqgopPUkUUOYFx41hcjJmb2eXOAzxQoL3mMYQBIqN0YgkTmgNM5JIS6R0YEqE\nlggXogJNjeaPv/02Il2hfvVV3n//HW7cfJK9gwPy+ZSnnvkQvW6Pjz57GeFaX4SxFdILdBgrrly/\nRqffbQH5TkwUBLjjPS5eWiVNWpmw0qJVCJzDuc5D3XhOFhVGaZpiwWQ64cLaBlJJKhWzKCs6sUYI\ngfMNjbfgDEoIamsI4pggCFBC01QVHljYdmAXUlEWc7QOqOOIukioqzlBnPCVV+7QWd4i7XR5vPeA\np599hkc7e4RBwGQy5s6dko889wwdtSCfK5wxRGHYWv8uXt6m2+uAoPUNOEsaxPTigG4na/VVWiGE\nR4caVzpq72iEZF4bHk9yjLP4czfao/3HXLt4md5gyE/ee8wzl4fEYYgOJE4IwLU+Y++w3uOsxTqD\n8NCcK++9b02dCEdVwSIHrTWNsdRo/vSVuyxfvsnFK9scnN7j9HQfKTzHh/usra3Q6/ZY7UmafMzx\n1ID3BGGIUhoZRgFSQq+TIbwnDiLO7r3P9toScRIhpEeKlnI0zmKB2sKiNOwfz5kUBm/azXJ+OfaP\nDun2evzpj+5T2dZ7XOQV1hqaqvoZB10UC+bzKUVRUFYl/jz7ty6bGmcdpmkwdcN0OqVxju/86B66\nv8LSyhLvvfcOzsDN69cZdDvUVY6pG5T3ZH7GfDajKHJm8zlnZ2fkixydpD2MD/jOt39MXRkC6flb\nX/wwcRwRhJooChHCgxQYIyhqQ2UsZ+MFd/dP+dnH/8t/50XOyfiM9QsX+cqrb/PLH7vR6p69R7j2\nvqZCtnW+qYnDiCAMEUi8gCgNUVJh6hqFxgtBkGRUSN4bQ2cU83hnlwd37xJFEa989zWssfT7fdaW\n19gchMRB07bA+J+55oQUyB+/8QE/+uEdjAsROuIzz11jdZCRplErN/CuFZJWDUVZUtaW8SRn93jG\nWWH4//vsHx0SpTGPFnD3cIF1kuksZzpbkOcVk9mc+TzHGIfFU5sGLyU/9VFZ585ZxJgwjEg6Hb71\nxiOCbIVO1uH9O+8yGi0hvKfb7TKdjinygiSOqMcPkHgW+YJFUdAYQ2MMx2cTlNDJy5zL8vqq4gsf\nvsbyqN92VUqhg7YMVFXNoqw5nczY2T3mtXd3KGr7/71bAWGksMawtXmB775xm83RkDRS4MEaj7Gt\n9bZqKopi0ZpJfvqOAGMQCIy1+JblY1p4/uwnpxgvKMsCUxeURU6SJDzaeUSaply9fJXQzbm4EiI4\nt+FWVQtd4YnjBO2qGcJZlHd88fMfpd/PCMIIiyBUikVeUpYFjW2Y5QvOxjMOz3LO5tW/usOfAcbQ\nHiAcn445Ph3zwrPP88++/yafe/oCF7ohmIYgbB0xcRwgrMOdTYjONSEq0K0NTwlqK4hUwO/+/reR\nvW36Scx7773T+gmdb0VwYYC3ljiMKB+/gxhdpCoaCufQOqSqC5IswViD+O2XnvbWOMq84cLWJv1B\nn95g0J4sniQMcOc0xsl4woOHR7zyxh12Fw5bN+ebVO2fVmCaFpFsXcMAxFHE9uY247MzbD7ms89c\nJlMOhCNL41ZcKiyBkkgtz9XvAqUVYRrxg/dOeXMPpNJ8cP99lkcjmrom63Q5ODpiqTdgbXWD5USx\nns7JkqjtAbRG0GJfWZa2hq+XPvXUy+WiZHm4xNrKEr1OKxmeFzVV0zDPc6y3TKYzprOco5MJWgr2\nzhoIe3jjQAYQhGD8eZoV50+9lS+2r7TwBEHI8sYWbz885J2H+3SzDOUF5txJak0LJrYCOYP1lnEV\n8Cc/3EVIxenpMXHY9krWOeI4wTYNgVZ04ozT3VusjzqY2mCspzENzoE4F78oJVFf+vTzL+fzgpVh\nn0C3gDrCI4VGqoDx6SlF2VriFouKhw93WF/pIaIOC9daa//VLH2+WaHOv2w3bZ1FINr3fRjDaGWd\ng2nNT+49onfOPAqpqMqybXWVZmZD/vC79/AqpKpKpPBtSbOGTtYhz3OUloyGI2xTEZg53SRs3wvg\nHNZ6lFZorVoqyDnUlUH08sbKMmnyU4GKxOGo6xxnG7qdFHn+NpXdvQPW11dY31zlxhNX+OHtU3SU\nYsryX3I9+PMQb9tDfEtSK61Jk4SybFGV2bT1G3aHS+Qi5v3dM/bGOfn/W8S588pRBGH0dE/3vHb2\neb33YiFHBJYQyDHI/AISyPjfjhABkhFGRlrfWXZ2Hv0kqDEEk0zW6urqrvpOfdFyGTO//tHz7v2A\nW91i5nmkrCpiisSV1Ltee4wxkBQf//6TQ2swShNTIGVJqNasikj0FIXCPJ6OHHYtbdOQ0WSlSD7T\nViUxZUKOkKM0z3zg5ZdfsNvvUNbSlYF77EAZUQZyBpX/32VdrTnsTtu0OO/Z77b0fU/TNHjvuV57\niSAfibrhdllAOVJKNG3N+PyJuIJsgjUrvBPLjRDEMcbakmG8Me4t735/z9evnig3NWWlUDmjyZS2\nQCuFPp+PtI3UwcbolaAV/K+sSspS3sLDMPDtm294OJ85nc+cTid++fkHyqoBu0HVW7BihCALV1Jm\nFh2ohue+J2UYp5F2s+F2HwirvJNzJubItMzcl5n7NDBMA/21/085SFmuK7+6PozjnRA9Kcq5jxk+\nfLry6uUjjS0wRqFzwhYZo7PMDmuFtkYL31haqrqm64QGqCqLUrD4SEiJf4aB0+OZh6cnNl1HXde8\n/uqB+/DMZv+C7CLYDsotn0EVEqBLsAegIMTANC0kEGClKpndgnNiVhSSJ+aAX9nKGD3GFIQok+a6\nUKQY0IUmxEBdV2SESUnAbY5opanKAqsUOUeCd8iMu1yXpq4aqrqUTIpCZ41aRwDIChcn/vp44fu3\nbzm+ONK0rVRTKRJT4s3rM799EPIGNJhOEpa7C1sMovjlA+N4oeu2eB9o247ZzRgjDKYLHlDoz3YV\nGmJOuGUieC9VVRZhLcVEWpPQ4hwxR7bthtt453IbeKi2kDObRl6LugBjpLWsq8quc35qzYBRgJac\niSkyzY7j6cThdOB03HPYbalKS9PWWFvw04/f0W62siit5bPNeq4FexL8ZweqZhhujOMoIR3EIUlr\nqZCqdTRXK413nsVJ7ywjlhXOOya34GJg8U4iAZgW+SdMmcb5gHOBeXYEH3GLl0osRf4F4fcF2gVZ\nK5kAAAAASUVORK5CYII=\n",
    "lattitude" -> "0.0",
    "schoolcode" -> "27511150621",
    "doctype" -> "image",
    "datestamp" -> "14-05-2018",
    "studentcode" -> "001",
    "longitude" -> "0.0",
    "timestamp" -> "12:40:19"
  ))

  def insertIntoDB(data: JsValue): String = {
    val client = DBConfigFactory.documentClient
    val result = client.createDocument(s"dbs/$databaseId/colls/$collectionId",
      new Document(data.toString()), null, false)
    println("record is inserted ==> ")
    "record is inserted"
  }
}

object InsertRegionData extends App {

  insertIntoDB(Json.obj(
    "doctype" -> "district",
    "name" -> "NANDED",
    "code" -> "27511"
  ))

  def insertIntoDB(data: JsValue): String = {
    val client = DBConfigFactory.documentClient
    val result = client.createDocument(s"dbs/$databaseId/colls/$collectionId",
      new Document(data.toString()), null, false)
    println("record is inserted ==> ")
    "record is inserted"
  }
}


object removelogData extends App {

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"attendance\" and " +
      " tyrion.schoolcode=\"27511150621\" and tyrion.datestamp=\"14-05-2018\" and tyrion.studentcode=\"oo1\"",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}


object getAllAMRData extends App {
  val amrData = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"attendance\"",
    null).getQueryIterable().toList()

  println("master data ===>" + amrData)
}

object getAllTHRData extends App {
  val thrData = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"thr\"",
    null).getQueryIterable().toList()

  println("master data ===>" + thrData)
}

object getAllHOTCookedData extends App {
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.schoolcode=\"27511150601\" and tyrion.studentcode = \"025\" and tyrion.datestamp = \"11-05-2018\"",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}

object getAllMasterLoginData extends App {
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"master-login\"",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}

object getImageData extends App {
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"image\" and tyrion.schoolcode=\"27511150601\" and tyrion.studentcode=\"016\"",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}

object getRegionData extends App {
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"state\"",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}


