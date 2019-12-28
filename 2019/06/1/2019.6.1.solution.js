const OrbitGraph = require('../OrbitGraph');

const solution = orbitData => OrbitGraph(orbitData).getOrbitsTotal();

module.exports = solution;
