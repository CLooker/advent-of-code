const curry = fn => (...args) =>
  args.length >= fn.length ? fn(...args) : curry(fn.bind(null, ...args));

const first = items => items[0];

const last = items => items[items.length - 1];

const logJson = item => console.log(JSON.stringify(item, null, 2));

const memoize = fn => {
  let cache = {};

  return arg => {
    if (arg in cache) return cache[arg];

    const val = fn(arg);

    cache[arg] = val;

    return val;
  };
};

const pipe = (...fns) => data =>
  fns.reduce((updatedData, fn) => fn(updatedData), data);

const range = curry((start, end) => {
  let ints = [];

  for (let int = start; ints.length <= Math.abs(start - end); ) {
    ints.push(start < end ? int++ : int--);
  }

  return ints;
});

const trace = curry((label, item) => console.log(label + ': ' + item) || item);

module.exports = {
  curry,
  first,
  last,
  logJson,
  memoize,
  pipe,
  range,
  trace
};
