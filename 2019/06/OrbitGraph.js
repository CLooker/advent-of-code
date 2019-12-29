const Graph = require('./Graph');

const findIntersection = (arrs = []) => {
  if (length in arrs && arrs.length < 2) return;

  for (let i = 1; i < arrs.length; i++) {
    const arr = arrs[i];
    const intersection = arr.find(item => arrs.every(ar => ar.includes(item)));
    if (intersection !== -1) return intersection;
  }
};

const OrbitGraph = (orbitData = []) => {
  const orbitGraph = orbitData.reduce((graph, orbitDatum) => {
    const [orbitee, orbiter] = orbitDatum.split(')');
    graph.addEdge(orbitee, orbiter);
    return graph;
  }, Graph());

  return {
    ...orbitGraph,
    getOrbitsTotal: () =>
      orbitGraph.values().reduce((orbitsTotal, edges) => {
        (function walk(currEdges) {
          currEdges.forEach(vertex => {
            orbitsTotal++;
            const nextEdges = orbitGraph.get(vertex);
            walk(nextEdges);
          });
        })(edges);

        return orbitsTotal;
      }, 0),
    getOrbitalTransfersTotal: (orbitItemNameA, orbitItemNameB) => {
      if (!orbitItemNameA || !orbitItemNameB) return 0;

      const vertexToRootPaths = [orbitItemNameA, orbitItemNameB].map(vertex => {
        const root = 'COM';
        let vertexToRootPath = [vertex];
        let lastStop = vertex;

        while (lastStop !== root) {
          const nextStop = orbitGraph.keys().find(v => {
            return orbitGraph.get(v).has(lastStop);
          });

          vertexToRootPath.push(nextStop);
          lastStop = nextStop;
        }

        return vertexToRootPath;
      });

      const commonVertex = findIntersection(vertexToRootPaths);

      return vertexToRootPaths.reduce(
        (accum, vertexToRootPath) =>
          accum + vertexToRootPath.indexOf(commonVertex) - 1,
        0
      );
    }
  };
};

module.exports = OrbitGraph;
