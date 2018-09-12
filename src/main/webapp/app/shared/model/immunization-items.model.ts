export interface IImmunizationItems {
  id?: string;
  title?: string;
  description?: string;
  startAge?: number;
  endAge?: number;
}

export const defaultValue: Readonly<IImmunizationItems> = {};
