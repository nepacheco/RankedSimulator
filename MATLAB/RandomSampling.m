%% Gaussian Distribution
sigma = 1;
mu = 0;

pdf = @(x,mu,sigma) 1/(sigma*sqrt(2*pi))*exp(-(x-mu)^2/(2*sigma^2));


num_samples = 100000;
samples = zeros(1,num_samples);
% for i = 1:n
%     sample(1,i) = rand()
% end


%% Gumbel
close all; clear; clc;

mu = 600;
beta = 200;
range = 100:.1:2900;
max_prob = 0.99999;
min_prob = 0.0001;
interval_prob = 0.01;
prob_range = min_prob:interval_prob:max_prob;

F = @(x) exp(-exp(-(x-mu)./beta));
f = @(x) exp(-((x-mu)/beta + exp(-(x-mu)/beta)))*1/beta;
x = @(P) -beta.*log(-log(P)) + mu;

figure 
hold on
plot(range,F(range),'LineWidth',2)
num_sims = 1000000;
pairs = zeros(num_sims,2);
for i = 1:num_sims
    y = rand();
    if y > max_prob
        y = max_prob;
    elseif y < min_prob
        y = min_prob;
    end
    pairs(i,:) = [x(y),y];
end
plot(x(prob_range),prob_range,'r--','LineWidth',3)
figure
plot(range,f(range),'r-','LineWidth',4)
hold on
histogram(pairs(:,1),1000,'Normalization','pdf');

%% Logistic Distribution
clc; clear; close all
mu = 1200;
beta = 200;
range = 100:.1:2900;
max_prob = 0.99999;
min_prob = 0.0001;
interval_prob = 0.01;
prob_range = min_prob:interval_prob:max_prob;

F = @(x) 1./(1 + exp(-(x-mu)./beta));
f = @(x) exp(-(x-mu)/beta)./(beta*(1 + exp(-(x-mu)./beta)).^2);
x = @(P) -beta.*log(P/(1-P)) + mu;

figure 
hold on
plot(range,F(range),'LineWidth',2)
num_sims = 1000000;
pairs = zeros(num_sims,2);
for i = 1:num_sims
    y = rand();
    if y > max_prob
        y = max_prob;
    elseif y < min_prob
        y = min_prob;
    end
    pairs(i,:) = [x(y),y];
end
plot(x(prob_range),prob_range,'r--','LineWidth',3)
figure
plot(range,f(range),'r-','LineWidth',2)
hold on
% histogram(pairs(:,1),1000,'Normalization','pdf');
