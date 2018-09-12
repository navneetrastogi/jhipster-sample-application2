import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IImmunizationPlan, defaultValue } from 'app/shared/model/immunization-plan.model';

export const ACTION_TYPES = {
  FETCH_IMMUNIZATIONPLAN_LIST: 'immunizationPlan/FETCH_IMMUNIZATIONPLAN_LIST',
  FETCH_IMMUNIZATIONPLAN: 'immunizationPlan/FETCH_IMMUNIZATIONPLAN',
  CREATE_IMMUNIZATIONPLAN: 'immunizationPlan/CREATE_IMMUNIZATIONPLAN',
  UPDATE_IMMUNIZATIONPLAN: 'immunizationPlan/UPDATE_IMMUNIZATIONPLAN',
  DELETE_IMMUNIZATIONPLAN: 'immunizationPlan/DELETE_IMMUNIZATIONPLAN',
  RESET: 'immunizationPlan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IImmunizationPlan>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ImmunizationPlanState = Readonly<typeof initialState>;

// Reducer

export default (state: ImmunizationPlanState = initialState, action): ImmunizationPlanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_IMMUNIZATIONPLAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IMMUNIZATIONPLAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_IMMUNIZATIONPLAN):
    case REQUEST(ACTION_TYPES.UPDATE_IMMUNIZATIONPLAN):
    case REQUEST(ACTION_TYPES.DELETE_IMMUNIZATIONPLAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_IMMUNIZATIONPLAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IMMUNIZATIONPLAN):
    case FAILURE(ACTION_TYPES.CREATE_IMMUNIZATIONPLAN):
    case FAILURE(ACTION_TYPES.UPDATE_IMMUNIZATIONPLAN):
    case FAILURE(ACTION_TYPES.DELETE_IMMUNIZATIONPLAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_IMMUNIZATIONPLAN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_IMMUNIZATIONPLAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_IMMUNIZATIONPLAN):
    case SUCCESS(ACTION_TYPES.UPDATE_IMMUNIZATIONPLAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_IMMUNIZATIONPLAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/immunization-plans';

// Actions

export const getEntities: ICrudGetAllAction<IImmunizationPlan> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_IMMUNIZATIONPLAN_LIST,
  payload: axios.get<IImmunizationPlan>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IImmunizationPlan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IMMUNIZATIONPLAN,
    payload: axios.get<IImmunizationPlan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IImmunizationPlan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IMMUNIZATIONPLAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IImmunizationPlan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IMMUNIZATIONPLAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IImmunizationPlan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IMMUNIZATIONPLAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
