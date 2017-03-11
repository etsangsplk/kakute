package edu.hku.cs.Optimization

import edu.hku.cs.Optimization.RuleCollector.{Rule, RuleSet}

/**
  * Created by jianyu on 3/4/17.
  */

/**
  * A [[RuleCollector]] collect the rule infer in a rdd operation
  * its id should be the rdd id of spark
*/

class RuleCollector(id: Int) {
  private var ruleSet: RuleSet = Map()

  def addRule(r: Rule): Unit = {
    val found = ruleSet.find(_._1 == r)
    val returnVal = if (found.isEmpty) {
      r -> 1
    } else {
      r -> (found.get._2 + 1)
    }
    ruleSet += returnVal
  }

  def collect(): Map[Rule, Int] = ruleSet
}

object RuleCollector {
  type Dependency = (Int, List[Int])
  type Rule = List[Dependency]
  type RuleSet = Map[Rule, Int]

  def CombineRule(rule_a: RuleSet, rule_b: RuleSet): RuleSet = {
    var returnVal = rule_a
    rule_b.foreach(rule => {
      val found = rule_a.find(_._1 == rule)
      if (found.isEmpty) {
        returnVal += rule
      } else {
        returnVal += rule._1 -> (rule._2 + found.get._2)
      }
    })
    returnVal
  }
}