const Graph = require('./Graph');

const OrbitGraph = (orbitData = []) => {
  const orbitGraph = orbitData.reduce((accum, orbitDatum) => {
    const [orbitee, orbiter] = orbitDatum.split(')');
    accum.addEdge(orbitee, orbiter);
    return accum;
  }, Graph());

  return {
    ...orbitGraph,
    getOrbitsTotal: () =>
      orbitGraph.values().reduce((accum, vertices) => {
        (function walk(currVertices) {
          currVertices.forEach(vertex => {
            accum++;
            const nextVertices = orbitGraph.get(vertex);
            walk(nextVertices);
          });
        })(vertices);

        return accum;
      }, 0)
  };
};

module.exports = OrbitGraph;
