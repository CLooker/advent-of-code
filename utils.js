const curry = fn => (...args) =>
  args.length >= fn.length ? fn(...args) : curry(fn.bind(null, ...args));

const first = items => items[0];

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

module.exports = {
  curry,
  first,
  memoize,
  pipe
};
