package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationAlternatingPositionCrossover;

/**
 * the test of the permutation EA with alternating position crossover
 */
public class PermutationEAAlternatingPositionCrossoverParentsDieTest
extends PermutationEAParentsDieTest {

  /** create */
  public PermutationEAAlternatingPositionCrossoverParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationAlternatingPositionCrossover());

    return ea;
  }
}
