const Graph = () => {
  let graph = {};

  const addEdge = (v1, v2) => {
    [v1, v2].forEach(addVertex);
    graph[v1].add(v2);
  };

  const addVertex = vertex => {
    if (!contains(vertex)) graph[vertex] = new Set();
  };

  const contains = vertex => vertex in graph;

  const get = vertex => graph[vertex];

  const keys = () => Object.keys(graph);

  const toJson = () =>
    Object.entries(graph).reduce((accum, [key, value]) => {
      accum[key] = Array.from(value);
      return accum;
    }, {});

  const values = () => Object.values(graph);

  return {
    addEdge,
    addVertex,
    contains,
    get,
    keys,
    toJson,
    values
  };
};

module.exports = Graph;
