package ex2

enum Question:
  case Relevance, Significance, Confidence, Final
  
trait ConferenceReviewing:
  def loadReview(article: Int, scores: Map[Question, Int]): Unit
  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit
  def orderedScores(article: Int, question: Question): List[Int]
  def averageFinalScore(article: Int): Double
  def acceptedArticles(): Set[Int]
  def sortedAcceptedArticles(): List[(Int, Double)]
  def averageWeightedFinalScoreMap(): Map[Int, Double]
  
object ConferenceReviewing:
  def apply(): ConferenceReviewing = ConferenceReviewingImpl()
  
  private class ConferenceReviewingImpl() extends ConferenceReviewing:
    override def loadReview(article: Int,
                            scores: Map[Question, Int]): Unit = ???

    override def loadReview(article: Int,
                            relevance: Int,
                            significance: Int,
                            confidence: Int,
                            fin: Int): Unit = ???

    override def orderedScores(article: Int,
                               question: Question): List[Int] = ???

    override def averageFinalScore(article: Int): Double = ???

    override def acceptedArticles(): Set[Int] = ???

    override def sortedAcceptedArticles(): List[(Int, Double)] = ???

    override def averageWeightedFinalScoreMap(): Map[Int, Double] = ???
    