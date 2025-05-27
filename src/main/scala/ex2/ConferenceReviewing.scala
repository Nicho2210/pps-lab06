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
    override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
      _reviews = (article,
        Map(Relevance -> relevance,
          Significance -> significance,
          Confidence -> confidence,
          Final -> fin)) :: _reviews

    override def orderedScores(article: Int, question: Question): List[Int] =
      _reviews.collect{ case (a, m) if a == article => m(question)}.sorted

    override def averageFinalScore(article: Int): Double =
      val s = _reviews.collect{ case (a, m) if a == article => m(Final)}
      val l = s.length
      s.map(_.toDouble).sum / l

    override def acceptedArticles(): Predef.Set[Int] =
      _reviews.filter((a, _) => averageFinalScore(a) > 5).filter((_, m) => m(Relevance) >= 8).map((a, _) => a).toSet

    override def sortedAcceptedArticles(): List[(Int, Double)] =
      acceptedArticles().map(a => (a, averageFinalScore(a))).toList.sortBy(_._2)
      
    private def averageWeightedFinalScoreMap(article: Int): Double =
      val s = _reviews.collect{ case (a, m) if a == article => (m(Confidence).toDouble * m(Final).toDouble)/10.0 }
      val l = s.length
      s.sum / l
      

    override def averageWeightedFinalScoreMap(): Predef.Map[Int, Double] =
      _reviews.map((a, m) => a).toSet.map(a => (a, averageWeightedFinalScoreMap(a))).toMap