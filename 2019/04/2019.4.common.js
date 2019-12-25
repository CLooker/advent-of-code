const { curry, pipe, range } = require('../../utils');

// common solution fn
const intToDigits = int => [...int.toString()].map(Number);

const every = curry((fns, arg) => fns.every(fn => fn(arg)));

const getValidPasswordsTotal = curry((validityFns, [min, max]) => {
  const isValidPassword = password =>
    pipe(intToDigits, every(validityFns))(password);

  return range(min, max).filter(isValidPassword).length;
});

// common validity fns
const hasLengthOfSix = items => items.length === 6;

const hasNoDescendingItem = items =>
  items.every((item, i) => i === items.length - 1 || items[i + 1] >= item);

const hasRepeatingItem = items =>
  items.some((item, i) => item === items[i + 1]);

module.exports = {
  getValidPasswordsTotal,
  hasLengthOfSix,
  hasNoDescendingItem,
  hasRepeatingItem
};
