package accounts

import models.Transaction
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}
import org.mockito.Mockito._

class ScreenDisplayTest extends FlatSpec with Matchers with BeforeAndAfterEach {

  var contentReaderMock: ContentReader = _

  override def beforeEach() = {
    contentReaderMock = mock(classOf[ContentReader])
  }

  behavior of "ContentReader Test"

  it should "Assert getFiles have been call at least once" in {
    val dir = "/document/transactions.csv"
    when(contentReaderMock.getFileContent(dir)).thenReturn(List[Transaction]())
    val sut = new ScreenDisplay(contentReaderMock, dir)
    sut.printAvg
    verify(contentReaderMock, atLeastOnce()).getFileContent(dir)
  }
}
