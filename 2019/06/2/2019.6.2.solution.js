const OrbitGraph = require('../OrbitGraph');

const solution = orbitData =>
  OrbitGraph(orbitData).getOrbitalTransfersTotal('YOU', 'SAN');

module.exports = solution;
