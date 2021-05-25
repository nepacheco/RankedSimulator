package SkillDistributions;

public class GumbelPlayerSkillDistribution extends PlayerSkillDistribution {

    public GumbelPlayerSkillDistribution(double minSkillValue, double maxSkillValue) {
        super(minSkillValue, maxSkillValue);
        //Auto-generated constructor stub
    }

    @Override
    double getSkillValue() {
        double skill = 0;
        // minProb is the probability at which a value of 100 or lower will be returned
        double minProb = 1.9E-8; // From RandomSampling.m based on std deviations of normal distribution
        // maxProb is the probability at which a value of 2900 or lower will be returned
        double maxProb = 0.99999998100; // From RandomSampling.m based on std deviations of normal distribution
        double w = 2.5; // This is a weight we found that produced nice curves taken from RandomSampling.m

        // Calculate the values used to scale the Gumbel Distribution.
        double beta = (this.maxSkillValue*w - this.minSkillValue/w)/(Math.log(-Math.log(minProb) - Math.log(-Math.log(maxProb))));
        double mu = this.minSkillValue/w + beta*Math.log(-Math.log(minProb));

        //Generate random uniform value between 0 and 1
        double probability = rand.nextDouble();
        //Player skill found using the inverse CDF of a Gumbel Distribution with parameters mu and beta
        skill = -beta*Math.log(-Math.log(probability)) + mu;
        
        return skill;
    }
    
}
