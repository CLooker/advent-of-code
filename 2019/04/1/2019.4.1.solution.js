const { curry, pipe, range } = require('../../../utils');

const intToDigits = int => [...int.toString()].map(Number);

const isLengthX = curry((x, items) => items && items.length === x);

const hasLengthOfSix = isLengthX(6);

const hasRepeatingItem = items =>
  items.some((item, i) => item === items[i + 1]);

const hasNoDescendingItem = items =>
  items.every((item, i) => i === items.length - 1 || items[i + 1] >= item);

const every = curry((fns, arg) => fns.every(fn => fn(arg)));

const getIsValidPassword = password =>
  pipe(
    intToDigits,
    every([hasLengthOfSix, hasRepeatingItem, hasNoDescendingItem])
  )(password);

const solution = ([min, max]) =>
  range(min, max).filter(getIsValidPassword).length;

module.exports = solution;
