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

  private case class ConferenceReviewingImpl() extends ConferenceReviewing:

    private var _reviews: List[(Int, Map[Question, Int])] = List.empty
    
    override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
      _reviews = (article, scores) :: _reviews
    
    import Question.*
    override def loadReview(article: Int, 
                            relevance: Int, 
                            significance: Int, 
                            confidence: Int, 
                            fin: Int): Unit =
      _reviews = (article, Map(Relevance -> relevance, 
        Significance -> significance, 
        Confidence -> confidence, 
        Final -> fin)) :: _reviews

    override def orderedScores(article: Int, question: Question): List[Int] =
      _reviews.filter((a, _) => a == article).map((_, m) => m).map(m => m(question)).sorted

    override def averageFinalScore(article: Int): Double = ???

    override def acceptedArticles(): Predef.Set[Int] = ???

    override def sortedAcceptedArticles(): List[(Int, Double)] = ???

    override def averageWeightedFinalScoreMap(): Predef.Map[Int, Double] = ???