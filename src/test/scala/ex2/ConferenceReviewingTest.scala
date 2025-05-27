package ex2

import org.junit.*
import org.junit.Assert.*

class ConferenceReviewingTest:
  val conference: ConferenceReviewing = ConferenceReviewing.apply()
  conference.loadReview(1, 8, 8, 6, 8)
  conference.loadReview(1, 9, 9, 6, 9)
  conference.loadReview(2, 9, 9, 10, 9)
  conference.loadReview(2, 4, 6, 10, 6)
  conference.loadReview(3, 3, 3, 3, 3)
  conference.loadReview(3, 4, 4, 4, 4)
  conference.loadReview(4, 6, 6, 6, 6)
  conference.loadReview(4, 7, 7, 8, 7)
  import ex2.Question.*
  val map: Map[Question, Int] = Map(Relevance -> 8, Significance -> 8, Confidence -> 7, Final -> 8)
  conference.loadReview(4, map)
  conference.loadReview(5, 6, 6, 6, 10)
  conference.loadReview(5, 7, 7, 7, 10)


  @Test def testOrderedScores(): Unit =
    assertEquals(conference.orderedScores(2, Relevance), List(4, 9))
    assertEquals(conference.orderedScores(4, Confidence), List(6, 7, 8))
    assertEquals(conference.orderedScores(5, Final), List(10, 10))

  @Test def testAverageFinalScore(): Unit =
    assertEquals(conference.averageFinalScore(1), 8.5, 0.01)
    assertEquals(conference.averageFinalScore(2), 7.5, 0.01)
    assertEquals(conference.averageFinalScore(3), 3.5, 0.01)
    assertEquals(conference.averageFinalScore(4), 7.0, 0.01)
    assertEquals(conference.averageFinalScore(5), 10.0, 0.01)

  @Test def testAcceptedArticles(): Unit =
    assertEquals(conference.acceptedArticles(), Set(1, 2, 4))

  @Test def testSortedAcceptedArticles(): Unit =
    assertEquals(conference.sortedAcceptedArticles(), List((4, 7.0), (2, 7.5), (1, 8.5)))

  @Test def optionalTestAverageWeightedFinalScore(): Unit =
    assertEquals(conference.averageWeightedFinalScoreMap()(1), (4.8+5.4)/2, 0.01)
    assertEquals(conference.averageWeightedFinalScoreMap()(2), (9.0+6.0)/2, 0.01)
    assertEquals(conference.averageWeightedFinalScoreMap()(3), (0.9+1.6)/2, 0.01)
    assertEquals(conference.averageWeightedFinalScoreMap()(4), (3.6+5.6+5.6)/3, 0.01)
    assertEquals(conference.averageWeightedFinalScoreMap()(5), (6.0+7.0)/2, 0.01)


